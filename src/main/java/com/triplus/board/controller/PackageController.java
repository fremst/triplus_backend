package com.triplus.board.controller;

import com.triplus.board.dto.PackageWithBoardDto;
import com.triplus.board.dto.PkgImgDto;
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

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<HashMap<String, Object>> getPackageList() {

        ArrayList<PackageWithBoardDto> packageWithBoardList = packageService.selectAllWithBoard();
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();

        for (PackageWithBoardDto dto : packageWithBoardList) {

            HashMap<String, Object> map = new HashMap<>();

            map.put("brdNum", dto.getBrdNum());
            map.put("title", dto.getTitle());
            map.put("adultPrice", dto.getAdultPrice());
            map.put("childPrice", dto.getChildPrice());
            map.put("tImg", dto.getTImg());
            map.put("region", dto.getRegion());

            int vacancy = getVacancy(dto, dto.getBrdNum());
            int rcrtCnt = dto.getRcrtCnt();
            String rcrtSta = getRecrtSta(dto.getSDate().toLocalDate(), vacancy, rcrtCnt);
            map.put("rcrtSta", rcrtSta);

            list.add(map);

        }

        return list;

    }

    @GetMapping(value = "/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> getPackageByBrdNum(
            @PathVariable("brdNum") int brdNum
    ) {

        HashMap<String, Object> data = new HashMap<>();

        PackageWithBoardDto packageWithBoardDto = packageService.selectWithBoard(brdNum);
        data.put("dto", packageWithBoardDto);

        HashMap<String, Object> map = new HashMap<>();

        map.put("period", new DateUtil().getPeriod(packageWithBoardDto.getSDate().toLocalDate(), packageWithBoardDto.getEDate().toLocalDate()));
        map.put("vacancy", getVacancy(packageWithBoardDto, packageWithBoardDto.getBrdNum()));

        ArrayList<PkgImgDto> pkgImgList = packageService.selectByBrdNum(brdNum);
        ArrayList<String> pkgImgOnlyList = new ArrayList<>();
        for (PkgImgDto pkgImgDto : pkgImgList) {
            pkgImgOnlyList.add(pkgImgDto.getPkgImg());
        }
        map.put("pkgImgs", pkgImgOnlyList);

        data.put("map", map);

        return data;

    }

    private int getVacancy(PackageWithBoardDto packageWithBoardDto, int brdNum) {

        HashMap<String, Object> cond = new HashMap<>();
        cond.put("resSta", "'예약', '승인'");
        cond.put("brdNum", brdNum);

        return packageWithBoardDto.getRcrtCnt() - packageService.getRecrtTotCnt(cond);

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
