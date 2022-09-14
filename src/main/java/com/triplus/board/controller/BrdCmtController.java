package com.triplus.board.controller;

import com.triplus.board.dto.BoardDto;
import com.triplus.board.dto.BrdCmtDto;
import com.triplus.board.service.BoardService;
import com.triplus.board.service.BrdCmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

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

    @PostMapping(value ="/api/section/magazines/comments/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String,String> insertComm(@PathVariable("brdNum") int brdNum,
                                             String id, String contents
                                             ){
        HashMap<String, String> map = new HashMap<>();
        try{
            BrdCmtDto comm=new BrdCmtDto(0, brdNum, id ,contents);
            int n1 = brdCmtService.insert(comm);
            if(n1 >0){
                map.put("result","success");
            }else {
                map.put("result","fail");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
