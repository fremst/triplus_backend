package com.triplus.board.controller;

import com.triplus.board.dto.BoardDto;
import com.triplus.board.dto.PlaceDto;
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
    PlaceService placeService;
    @Autowired
    BoardService boardService;

    @Autowired
    McatService mcatService;

    @Autowired
    ScatService scatService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<PlaceDto> placeSelectAll() {
        return placeService.selectAll();
    }

    @GetMapping(value = "/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public PlaceDto placeSelect(@PathVariable("brdNum") int brdNum) {
        return placeService.select(brdNum);
    }

    @PostMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
//    @Transactional
    public HashMap<String, String> placeInsert(String userId, String title, String overview,
                                               String firstimage, String mcatName, String scatName, String region, String addr, String tel,
                                               int mapx, int mapy, String homepage) {

        int mcatNum = mcatService.select(mcatName);
        int scatNum = scatService.select(scatName);

        int brdNum = boardService.getNextBrdNum();

        int boardResult = boardService.fixedInsert(
                new BoardDto(
                        brdNum,
                        userId,
                        title,
                        overview,
                        firstimage,
                        null,
                        0,
                        true
                )
        );

        int placeResult = placeService.insert(
                new PlaceDto(
                        brdNum,
                        mcatNum,
                        scatNum,
                        region,
                        addr,
                        tel,
                        mapx,
                        mapy,
                        homepage // url
                )
        );

        HashMap<String, String> result = new HashMap<>();
        if (boardResult > 0 && placeResult > 0) {
            result.put("result", "success");
        } else {
            result.put("result", "fail");
        }
        return result;

    }

    @PutMapping(value = "/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> placeUpdate(@PathVariable("brdNum") int brdNum, String userId, String title, String overview,
                                               String firstimage, String mcatName, String scatName, String region, String addr, String tel,
                                               int mapx, int mapy, String homepage) {

        int n1 = boardService.bPlaceUpdate(new BoardDto(brdNum, null, title, overview, firstimage, null, 0, true));
        int n2 = placeService.update(new PlaceDto(brdNum, 1, 1, region, addr, tel, mapx, mapy, homepage));
        HashMap<String, String> result = new HashMap<>();
        if (n1 > 0 && n2 > 0) {
            result.put("result", "success");
        } else {
            result.put("result", "fail");
        }
        return result;

    }

    @DeleteMapping(value = "/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> placeDelete(@PathVariable("brdNum") int brdNum) {
        int deleteResult = placeService.delete(brdNum);
        HashMap<String, String> result = new HashMap<>();
        if (deleteResult > 0) {
            result.put("result", "success");
        } else {
            result.put("result", "fail");
        }
        return result;
    }
}

