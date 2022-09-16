package com.triplus.board.service;

import com.triplus.board.dto.FaqDto;
import com.triplus.board.mapper.FaqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FaqService {

    @Autowired
    private FaqMapper faqMapper;

    public ArrayList<FaqDto> selectAll() {
        return faqMapper.selectAll();
    }

    public int insert(FaqDto faqDto) {
        return faqMapper.insert(faqDto);
    }

    public int delete(int faqNum) {
        return faqMapper.delete(faqNum);
    }

    public FaqDto select(int faqNum) {
        return faqMapper.select(faqNum);
    }

    public int update(FaqDto faqDto) {
        return faqMapper.update(faqDto);
    }

}
