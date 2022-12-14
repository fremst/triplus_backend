package com.triplus.board.controller;

import com.triplus.board.dto.FaqDto;
import com.triplus.board.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RequestMapping("/api/service/faqs")
@CrossOrigin("*")
@RestController
public class FaqController {

    @Autowired
    FaqService faqService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<FaqDto> selectAll() {

        return faqService.selectAll();

    }

    @GetMapping(value = "/{faqNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public FaqDto select(@PathVariable("faqNum") int faqNum) {

        return faqService.select(faqNum);

    }

    @PostMapping(value = {"/package/", "/payment/", "/reservation/", "/user/"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> insert(FaqDto faqDto) {

        int faqResult = faqService.insert(faqDto);

        HashMap<String, String> result = new HashMap<>();
        if (faqResult > 0) {
            result.put("result", "success");
        } else {
            result.put("result", "fail");
        }
        return result;

    }

    @PutMapping(value = "/{faqNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> faqUpdate(@PathVariable("faqNum") int faqNum, @RequestBody FaqDto faqDto) {

        System.out.println(faqNum);
        faqDto.setFaqNum(faqNum);
        int n = faqService.update(faqDto);
        HashMap<String, String> result = new HashMap<>();
        if (n > 0) {
            result.put("result", "success");
        } else {
            result.put("result", "fail");
        }
        return result;

    }

    @DeleteMapping(value = "/{faqNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> faqDelete(@PathVariable("faqNum") int faqNum) {

        HashMap<String, String> result = new HashMap<>();
        try {
            int deleteResult = faqService.delete(faqNum);

            if (deleteResult > 0) {
                result.put("result", "success");
            } else {
                result.put("result", "fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
