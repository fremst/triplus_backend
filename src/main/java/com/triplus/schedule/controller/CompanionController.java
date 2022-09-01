package com.triplus.schedule.controller;

import com.triplus.schedule.dto.CompanionDto;
import com.triplus.schedule.service.CompanionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@CrossOrigin("*")
@RestController
public class CompanionController {
    @Autowired
    CompanionService companionService;

    @PostMapping(value = "/api/member/plan/companion", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> addCompanion(CompanionDto dto) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        int n = companionService.addCompanion(dto);
        if (n > 0) {
            result.put("result", "success");
        } else {
            result.put("result", "fail");
        }
        return result;
    }

    @GetMapping(value = "/api/member/plan/companion/identify", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> identifyCompanion(String id) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        CompanionDto dto = companionService.find(id);

        if (dto != null) { //존재하면
            result.put("result", "success");
        } else {
            result.put("result", "fail");
        }
        return result;
    }


}