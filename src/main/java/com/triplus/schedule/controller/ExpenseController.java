package com.triplus.schedule.controller;

import com.triplus.schedule.dto.ExpenseDto;
import com.triplus.schedule.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin("*")
@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping(value = "/api/member/plan/expense/{skdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ExpenseDto getExpense(@PathVariable int skdNum)
    {
        return expenseService.select(skdNum);
    }

    @PutMapping(value = "/api/service/plan/expense/{skdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> postUpdate(
            @PathVariable int skdNum,
            @RequestBody ExpenseDto dto) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", false);
        map.put("reason", "unknown");
        try {
            int result = expenseService.update(dto);
            map.put("result", result > 0);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("reason", "DB 오류");
        }
        return map;
    }
}