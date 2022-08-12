package com.triplus.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.triplus.board.dto.QnaDto;
import com.triplus.board.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin("*")
@RestController
public class QnaController
{
    // static
    private static ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
    }

    // properties
    @Autowired private QnaService qnaService;

    @GetMapping(value = "/api/service/qna/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<QnaDto> getList()
    {
        return qnaService.selectList();
    }
    @GetMapping(value = "/api/service/qna/detail", produces = {MediaType.APPLICATION_JSON_VALUE})
    public QnaDto getDetail(int num)
    {
        return qnaService.selectOne(num);
    }
}
