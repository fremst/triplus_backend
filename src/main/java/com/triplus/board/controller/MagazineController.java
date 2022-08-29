package com.triplus.board.controller;

import com.triplus.board.dto.MagazineDto;
import com.triplus.board.service.BoardService;
import com.triplus.board.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin("*")
@RestController
public class MagazineController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private MagazineService magazineService;

    @GetMapping(value= "/api/section/magazines", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<MagazineDto> getList(){
        return magazineService.selectAll();
    }

    @GetMapping(value ="/api/section/magazines/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MagazineDto getDetail(@PathVariable("brdNum") int brdNum){
        boardService.updateHit(brdNum);
        return magazineService.select(brdNum);
    }
    @DeleteMapping(value ="/api/section/magazines/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteMagazine(@PathVariable("brdNum") int brdNum){
        int n1 = magazineService.delete(brdNum);
        int n2 = boardService.delete(brdNum);

        if(n1 > 0 && n2 > 0){
            return "success";
        }else {
            return "fail";
        }
    }
}
