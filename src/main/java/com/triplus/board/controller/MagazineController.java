package com.triplus.board.controller;

import com.triplus.board.dto.*;
import com.triplus.board.service.BoardService;
import com.triplus.board.service.BrdCmtService;
import com.triplus.board.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin("*")
@RestController
public class MagazineController {
    @Autowired
    private BrdCmtService brdCmtService;

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
    @DeleteMapping(value ="/api/admin/magazines/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteMagazine(@PathVariable("brdNum") int brdNum){
        try{

                brdCmtService.deleteAll(brdNum);

                magazineService.delete(brdNum);

                boardService.delete(brdNum);

        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }
    @PostMapping(value ="/api/admin/magazines", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> writeMagazine(MultipartHttpServletRequest request,
                                                 BoardDto boardDto,
                                                 String category
                                                 ){

        HashMap<String, String> map = new HashMap<>();
        MultipartFile tImgFile = request.getFile("tImgFile");
        try {
            int brdNum = boardService.getNextBrdNum();
            boardDto.setBrdNum(brdNum);
            boardDto.setTImg(tImgFile.getBytes());
            boardService.fixedInsert(boardDto);


            MagazineDto magazineDto = new MagazineDto(boardDto , category);
            magazineService.insert(magazineDto);
            map.put("result","success");

        }catch (Exception e){

            e.printStackTrace();
            map.put("result","fail");

        }

        return map;
    }
    @PostMapping(value = "/api/admin/magazines/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> updateMagazine(@PathVariable("brdNum") int brdNum, MultipartHttpServletRequest request,
                                                  BoardDto boardDto, String category
    ) {
        HashMap<String, String> map = new HashMap<>();
        MultipartFile tImgFile = request.getFile("tImgFile");
        System.out.println("writerId:"+ boardDto.getWriterId() + ", tImg:" + boardDto.getTImg()+ ", Title:" +  boardDto.getTitle()+ ", Contents:" + boardDto.getContents()+ ", Category:" + category);
        try {

//            BoardDto board = new BoardDto(brdNum, boardDto.getWriterId(), boardDto.getTitle(), boardDto.getContents(), tImgFile, null, 0, true);
            boardDto.setTImg(tImgFile.getBytes());
            int n1 = boardService.update(boardDto);
            MagazineDto magazineDto = new MagazineDto(boardDto , category);
            int n2 = magazineService.update(magazineDto);
            if (n1 > 0 && n2 > 0) {
                map.put("result", "success");
            } else {
                map.put("result", "fail");
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return map;

    }
}
