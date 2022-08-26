package com.triplus.board.controller;

import com.triplus.board.dto.BrdCmtDto;
import com.triplus.board.service.BoardService;
import com.triplus.board.service.BrdCmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin("*")
@RestController
public class BrdCmtController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BrdCmtService brdCmtService;

    @GetMapping(value= "/api/section/magazines/comments/{brdNum}", produces ={MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<BrdCmtDto> getCommList(@PathVariable("brdNum") int brdNum){
        return brdCmtService.select(brdNum);
    }
    @DeleteMapping(value ="/api/section/magazines/comments/{brdCmtNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteMagazine(@PathVariable("brdCmtNum") @RequestBody int brdCmtNum){
        int n = brdCmtService.delete(brdCmtNum);
        if(n > 0){
            return "success";
        }else {
            return "fail";
        }
    }
}
