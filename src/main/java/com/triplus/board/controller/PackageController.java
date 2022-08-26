package com.triplus.board.controller;

import com.triplus.board.dto.PackageDto;
import com.triplus.board.dto.PkgImgDto;
import com.triplus.board.mapper.PkgImgMapper;
import com.triplus.board.service.PackageService;
import com.triplus.board.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@RequestMapping("/api/section/packages")
@CrossOrigin("*")
@RestController
public class PackageController {

    @Autowired
    PackageService packageService;

    @Autowired
    PkgImgMapper pkgImgMapper;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<HashMap<String, Object>> getPackageList() {

        ArrayList<PackageDto> packageDtos = packageService.selectAll();
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();

        for (PackageDto packageDto : packageDtos) {

            HashMap<String, Object> map = new HashMap<>();

            map.put("brdNum", packageDto.getBrdNum());
            map.put("title", packageDto.getTitle());
            map.put("adultPrice", packageDto.getAdultPrice());
            map.put("childPrice", packageDto.getChildPrice());
            map.put("tImg", packageDto.getTImg());
            map.put("region", packageDto.getRegion());

            int vacancy = getVacancy(packageDto, packageDto.getBrdNum());
            int rcrtCnt = packageDto.getRcrtCnt();
            String rcrtSta = getRecrtSta(packageDto.getSDate().toLocalDate(), vacancy, rcrtCnt);
            map.put("rcrtSta", rcrtSta);

            data.add(map);

        }

        return data;

    }

    @GetMapping(value = "/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> getPackageByBrdNum(
            @PathVariable("brdNum") int brdNum
    ) {

        HashMap<String, Object> data = new HashMap<>();

        PackageDto packageDto = packageService.select(brdNum);
        data.put("dto", packageDto);

        HashMap<String, Object> map = new HashMap<>();

        map.put("period", new DateUtil().getPeriod(packageDto.getSDate().toLocalDate(), packageDto.getEDate().toLocalDate()));
        map.put("vacancy", getVacancy(packageDto, packageDto.getBrdNum()));

        ArrayList<PkgImgDto> pkgImgDtos = pkgImgMapper.selectByBrdNum(brdNum);
        ArrayList<byte[]> pkgImgs = new ArrayList<>();
        for (PkgImgDto pkgImgDto : pkgImgDtos) {
            pkgImgs.add(pkgImgDto.getPkgImg());
        }
        map.put("pkgImgs", pkgImgs);

        data.put("map", map);

        return data;

    }

    private int getVacancy(PackageDto packageDto, int brdNum) {

        HashMap<String, Object> cond = new HashMap<>();
        cond.put("resSta", "'대기', '확정'");
        cond.put("brdNum", brdNum);

        return packageDto.getRcrtCnt() - packageService.getRcrtTotCnt(cond);

    }

    private String getRecrtSta(LocalDate sDate, int vacancy, int rcrtCnt) {

        int remDays = new DateUtil().getDaysBetween(LocalDate.now(), sDate);
        String rcrtSta = "모집중";
        if (remDays <= 7 + 7 || vacancy <= rcrtCnt * 0.2) {
            rcrtSta = "마감임박";
        }
        if (remDays <= 7 || vacancy == 0) {
            rcrtSta = "모집완료";
        }
        return rcrtSta;

    }

}
