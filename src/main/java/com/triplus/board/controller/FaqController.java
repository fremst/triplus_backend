package com.triplus.board.controller;

import com.triplus.board.dto.FaqDto;
import com.triplus.board.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RequestMapping("/api/section/faqs")
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

    @PostMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> insert(int faqNum, String id, String category,
                                          String faqTitle, String faqContent) {
        int faqResult = faqService.insert(
                new FaqDto(
                        faqNum,
                        id,
                        category,
                        faqTitle,
                        faqContent
                )
        );

        HashMap<String, String> result = new HashMap<>();
        if (faqResult > 0) {
            result.put("result", "success");
        } else {
            result.put("result", "fail");
        }
        return result;

    }

    @PutMapping(value = "/{faqNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> faqUpdate(@PathVariable("faqNum") int faqNum, String id, String category, String faqTitle, String faqContent) {

        int n = faqService.update(new FaqDto(faqNum, id, category, faqTitle, faqContent));
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
