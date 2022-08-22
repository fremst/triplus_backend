package com.triplus.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.triplus.board.dto.BoardDto;
import com.triplus.board.dto.NoticeDto;
import com.triplus.board.dto.NoticeWithBoardDto;
import com.triplus.board.service.BoardService;
import com.triplus.board.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin("*")
@RestController
public class NoticeController {
    // static
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    // properties
    @Autowired
    private BoardService boardService;
    @Autowired
    private NoticeService noticeService;

    @GetMapping(value = "/api/service/notices", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<NoticeDto> getList() {

        return noticeService.getPageList();
    }

    @GetMapping(value = "/api/service/notices/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public NoticeDto getDetail(@PathVariable("brdNum") int num) {
        // 조회수 기능 추가
        boardService.updateHit(num);

        return noticeService.select(num);
    }

    @PostMapping(value = "/api/service/notices/write", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> writeNotice(
            String writerId, long noticeNum,
            String title, String category,
            String contents) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("result", false);
        map.put("reason", "unknown");

        try {
            int brdNum = boardService.getNextBrdNum();
            BoardDto board = new BoardDto(brdNum, writerId, title, contents ,"",null, 0, true);
            int result1 = boardService.fixedInsert(board);
            if (result1 <= 0) {
                map.put("reason", "Board Service Error");
                return map;
            }
            NoticeDto notice = new NoticeDto(board,category,noticeNum);
            int result2 = noticeService.insert(notice);
            if (result2 <= 0) {
                map.put("reason", "Notice Service Error");
                return map;
            }
            map.put("result", result1 > 0 && result2 > 0);
            map.put("brdNum", brdNum);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("reason", "DB 오류");
        }
        return map;
    }


    @DeleteMapping(value = "/api/service/notices/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteNotice(@PathVariable("brdNum") int brdNum) {
            int n1 = noticeService.delete(brdNum);
            int n2 = boardService.delete(brdNum);
            if(n1>0 && n2>0){
                return "success";
            }else {
                return "fail";
            }
        }
    @GetMapping(value= "/api/service/notices/{brdNum}/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    public NoticeDto prevDetail(@PathVariable("brdNum") int brdNum, Model model){
        NoticeDto notice= noticeService.select(brdNum);
        return notice;
    }

    @PutMapping(value= "/api/service/notices/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> updateNotice(@PathVariable("brdNum") int brdNum,
                                                @RequestBody NoticeWithBoardDto noticeWithBoardDto
                                                ){
        HashMap<String, String> map = new HashMap<String, String>();
        System.out.println(noticeWithBoardDto.getTitle() + noticeWithBoardDto.getCategory() + noticeWithBoardDto.getContents() + brdNum);
        try {
            BoardDto board = new BoardDto(brdNum, null, noticeWithBoardDto.getTitle(), noticeWithBoardDto.getContents(), "", null, 0, true);
            int n1 = boardService.updateNotice(board);
            NoticeDto notice = new NoticeDto(board, noticeWithBoardDto.getCategory(),0);
            int n2 = noticeService.update(notice);
            if (n1 > 0 && n2 > 0) {
                map.put("result", "true");
            } else {
                map.put("result", "false");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
