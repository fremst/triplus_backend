package com.triplus.board.controller;

import com.triplus.board.dto.PackageWithBoardDto;
import com.triplus.board.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
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
    public ArrayList<HashMap<String, Object>> getPackage() {

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
            map.put("recStatus", getRecStatus(dto));

            list.add(map);

        }

        return list;

    }

    private String getRecStatus(PackageWithBoardDto dto) {
        // 수정 예정. 패키지 동행자로부터 남은 인원 추출하고, 시작일로부터 마감일 추출.
        String recStatus = "모집중";
        return recStatus;
    }

    @GetMapping(value = "/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public PackageWithBoardDto getPackageByBrdNum(
            @PathVariable("brdNum") int brdNum
    ) {
        return packageService.selectWithBoard(brdNum);
    }

}
