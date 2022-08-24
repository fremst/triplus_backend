package com.triplus.board.controller;

import com.triplus.board.dto.PlaceDto;
import com.triplus.board.dto.PlaceRequestData;
import com.triplus.board.service.BoardService;
import com.triplus.board.service.McatService;
import com.triplus.board.service.PlaceService;
import com.triplus.board.service.ScatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RequestMapping("/api/section/places")
@CrossOrigin("*")
@RestController
public class PlaceController {

    @Autowired
    BoardService boardService;

    @Autowired
    PlaceService placeService;

    @Autowired
    McatService mcatService;

    @Autowired
    ScatService scatService;


    @GetMapping(value = "/attraction", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<HashMap<String, Object>> selectAllAttraction() {

        String mcatName = "명소";

        return getData(mcatName);

    }

    @GetMapping(value = "/restaurant", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<HashMap<String, Object>> selectAllRestaurant() {

        String mcatName = "맛집";

        return getData(mcatName);

    }

    @GetMapping(value = "/accommodation", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<HashMap<String, Object>> selectAllAccommodation() {

        String mcatName = "숙소";

        return getData(mcatName);

    }

    private ArrayList<HashMap<String, Object>> getData(String mcatName) {

        ArrayList<PlaceDto> placeDtos = placeService.selectAllByMcatNum(mcatService.selectByMcatName(mcatName).getMcatNum());
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();

        for (PlaceDto placeDto : placeDtos) {

            HashMap<String, Object> map = new HashMap<>();
            map.put("brdNum", placeDto.getBrdNum());
            map.put("title", placeDto.getTitle());
            map.put("region", placeDto.getRegion());
            map.put("addr", placeDto.getAddr());
            map.put("tel", placeDto.getTel());
            map.put("firstimage", placeDto.getTImg());

            HashMap<String, Integer> scatMap = new HashMap<>();
            scatMap.put("mcatNum", placeDto.getMcatNum());
            scatMap.put("scatNum", placeDto.getScatNum());
            map.put("scatName", scatService.select(scatMap).getScatName());

            map.put("overview", placeDto.getContents());
            data.add(map);
        }

        return data;

    }

    @GetMapping(value = {"/attraction/{brdNum}", "/restaurant/{brdNum}", "/accommodation/{brdNum}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> select(@PathVariable("brdNum") int brdNum) {

        PlaceDto placeDto = placeService.select(brdNum);
        HashMap<String, Object> data = new HashMap<>();

        data.put("brdNum", placeDto.getBrdNum());
        data.put("title", placeDto.getTitle());
        data.put("region", placeDto.getRegion());
        data.put("addr", placeDto.getAddr());
        data.put("tel", placeDto.getTel());
        data.put("firstimage", placeDto.getTImg());

        data.put("mcatName", mcatService.select(placeDto.getMcatNum()).getMcatName());
        HashMap<String, Integer> scatMap = new HashMap<>();
        scatMap.put("mcatNum", placeDto.getMcatNum());
        scatMap.put("scatNum", placeDto.getScatNum());
        data.put("scatName", scatService.select(scatMap).getScatName());

        data.put("overview", placeDto.getContents());

        data.put("mapx", placeDto.getMapx());
        data.put("mapy", placeDto.getMapy());
        data.put("homepage", placeDto.getUrl());

        return data;

    }

    @PostMapping(value = {"/attraction/", "/restaurant/", "/accommodation/"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> insert(PlaceRequestData placeRequestData) {

        int serviceResult = 0;
        HashMap<String, String> result = new HashMap<>();

        try {

            serviceResult = placeService.insert(
                    new PlaceDto(
                            0,
                            null,
                            placeRequestData.getTitle(),
                            placeRequestData.getOverview(),
                            placeRequestData.getFirstimage(),
                            placeRequestData.getWDate(),
                            0,
                            placeRequestData.isPublished(),
                            mcatService.selectByMcatName(placeRequestData.getMcatName()).getMcatNum(),
                            scatService.selectByScatName(placeRequestData.getScatName()).getScatNum(),
                            placeRequestData.getRegion(),
                            placeRequestData.getAddr(),
                            placeRequestData.getTel(),
                            placeRequestData.getMapx(),
                            placeRequestData.getMapy(),
                            placeRequestData.getHomepage()
                    )
            );

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (serviceResult > 0) {

                result.put("result", "success");

            } else {

                result.put("result", "fail");

            }

        }

        return result;

    }

    @PutMapping(value = {"/attraction/{brdNum}", "/restaurant/{brdNum}", "/accommodation/{brdNum}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> update(@PathVariable("brdNum") int brdNum, @RequestBody PlaceRequestData placeRequestData) {

        int serviceResult = 0;
        HashMap<String, String> result = new HashMap<>();

        try {

            serviceResult = placeService.update(
                    new PlaceDto(
                            brdNum,
                            null,
                            placeRequestData.getTitle(),
                            placeRequestData.getOverview(),
                            placeRequestData.getFirstimage(),
                            null,
                            boardService.select(brdNum).getHit(),
                            placeRequestData.isPublished(),
                            mcatService.selectByMcatName(placeRequestData.getMcatName()).getMcatNum(),
                            scatService.selectByScatName(placeRequestData.getScatName()).getScatNum(),
                            placeRequestData.getRegion(),
                            placeRequestData.getAddr(),
                            placeRequestData.getTel(),
                            placeRequestData.getMapx(),
                            placeRequestData.getMapy(),
                            placeRequestData.getHomepage()
                    )
            );

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (serviceResult > 0) {
                result.put("result", "success");
            } else {
                result.put("result", "fail");
            }

        }

        return result;

    }

    @DeleteMapping(value = {"/attraction/{brdNum}", "/restaurant/{brdNum}", "/accommodation/{brdNum}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> delete(@PathVariable("brdNum") int brdNum) {

        int serviceResult = 0;
        HashMap<String, String> result = new HashMap<>();

        try {
            serviceResult = placeService.delete(brdNum);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (serviceResult > 0) {

                result.put("result", "success");

            } else {

                result.put("result", "fail");

            }

        }

        return result;

    }

}