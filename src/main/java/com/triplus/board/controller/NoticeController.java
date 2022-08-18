package com.triplus.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.triplus.board.dto.BoardDto;
import com.triplus.board.dto.NoticeDto;
import com.triplus.board.service.BoardService;
import com.triplus.board.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

        return noticeService.select(num);
    }

    @PostMapping(value = "/api/service/notices/write", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> noticeWrite(
            String writerId, long noticeNum,
            String title, String category,
            String contents) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("result", false);
        map.put("reason", "unknown");

        try {
            int brdNum = boardService.getNextBrdNum();
            BoardDto board = new BoardDto(brdNum, writerId, title, contents , "" ,null, 0, true);
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
    public String noticeDelete(@PathVariable("brdNum") int brdNum) {
            int n1 = noticeService.delete(brdNum);
            int n2 = boardService.delete(brdNum);
            if(n1>0 && n2>0){
                return "success";
            }else {
                return "fail";
            }

        }

    }
