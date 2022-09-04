package com.triplus.board.controller;

import com.triplus.board.dto.PackageDto;
import com.triplus.board.dto.PkgImgDto;
import com.triplus.board.mapper.PkgImgMapper;
import com.triplus.board.service.PackageService;
import com.triplus.board.util.DateUtil;
import com.triplus.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/api/section/packages")
@CrossOrigin("*")
@RestController
public class PackageController {

    @Autowired
    PackageService packageService;

    @Autowired
    PkgImgMapper pkgImgMapper;

    @Autowired
    ReservationService reservationService;

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

    @GetMapping(value = "/cond/{rcrtSta}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<HashMap<String, Object>> getPackageListForAdmin(
            @PathVariable("rcrtSta") String rcrtSta
    ) {

        switch (rcrtSta) {
            case "proceeding":
                rcrtSta = "모집중";
                break;
            case "ending":
                rcrtSta = "마감임박";
                break;
            case "completed":
                rcrtSta = "모집완료";
                break;
        }

        ArrayList<PackageDto> packageDtos = packageService.selectAllForAdmin();
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();

        for (PackageDto packageDto : packageDtos) {

            int vacancy = getVacancy(packageDto, packageDto.getBrdNum());
            int rcrtCnt = packageDto.getRcrtCnt();

            if(rcrtSta.equals(getRecrtSta(packageDto.getSDate().toLocalDate(), vacancy, rcrtCnt))){
                HashMap<String, Object> map = new HashMap<>();
                map.put("brdNum", packageDto.getBrdNum());
                map.put("title", packageDto.getTitle());
                map.put("sDate", packageDto.getSDate());
                map.put("eDate", packageDto.getEDate());
                map.put("adultPrice", packageDto.getAdultPrice());
                map.put("childPrice", packageDto.getChildPrice());
                map.put("region", packageDto.getRegion());
                map.put("rcrtCnt", packageDto.getRcrtCnt());
                map.put("vacancy", vacancy);

                map.put("resList",reservationService.selectByBrdNum(packageDto.getBrdNum()));

                data.add(map);

            }

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

        map.put("pkgImgDtos", pkgImgDtos);

        data.put("map", map);

        return data;

    }

    @PostMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> insert(MultipartHttpServletRequest request, PackageDto packageDto) {

        HashMap<String, Object> result = new HashMap<>();

        MultipartFile tImgFile = request.getFile("tImgFile");
        List<MultipartFile> pkgImgFiles = request.getFiles("pkgImgFiles");

        try {

            ArrayList<PkgImgDto> pkgImgDtos = new ArrayList<>();

            for(MultipartFile pkgImgFile:pkgImgFiles){

                PkgImgDto pkgImgDto = new PkgImgDto(
                        0,
                        0,
                        pkgImgFile.getBytes(),
                        pkgImgFile.getOriginalFilename(),
                        pkgImgFile.getSize()
                );

                pkgImgDtos.add(pkgImgDto);

            }

            assert tImgFile != null;
            packageDto.setTImg(tImgFile.getBytes());

            packageService.insert(packageDto, pkgImgDtos);

            result.put("brdNum", packageDto.getBrdNum());
            result.put("result", "success");

        } catch (Exception e) {

            e.printStackTrace();
            result.put("result", "fail");

        }

        return result;

    }

    @PostMapping(value = "/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> update(
            @PathVariable("brdNum") int brdNum,
            MultipartHttpServletRequest request,
            PackageDto packageDto
            ) {

        HashMap<String, Object> result = new HashMap<>();

        MultipartFile tImgFile = request.getFile("tImgFile");
        List<MultipartFile> pkgImgFiles = request.getFiles("pkgImgFiles");

        try {

            ArrayList<PkgImgDto> pkgImgDtos = new ArrayList<>();

            for(MultipartFile pkgImgFile:pkgImgFiles){
                PkgImgDto pkgImgDto = new PkgImgDto(
                        0,
                        0,
                        pkgImgFile.getBytes(),
                        pkgImgFile.getOriginalFilename(),
                        pkgImgFile.getSize()
                );

                pkgImgDtos.add(pkgImgDto);

            }

            assert tImgFile != null;
            packageDto.setTImg(tImgFile.getBytes());

            packageService.update(packageDto, pkgImgDtos);

            result.put("brdNum", packageDto.getBrdNum());
            result.put("result", "success");

        } catch (Exception e) {

            e.printStackTrace();
            result.put("result", "fail");

        }

        return result;

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
