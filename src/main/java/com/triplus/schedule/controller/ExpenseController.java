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

    @GetMapping(value = "/api/member/plan/{skdNum}/expense", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ExpenseDto getExpense(@PathVariable int skdNum)
    {
        try
        {
            System.out.println(skdNum);
            ExpenseDto dto = expenseService.select(skdNum);
            if (dto == null) {
                System.out.println("create new DTO");
                dto = new ExpenseDto(skdNum, "[]");
                expenseService.insert(dto);
                System.out.println("create new DTO OK");
            }
            return dto;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping(value = "/api/member/plan/{skdNum}/expense", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> putUpdate(
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