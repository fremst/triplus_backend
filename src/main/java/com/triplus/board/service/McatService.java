package com.triplus.board.service;

import com.triplus.board.mapper.McatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class McatService {
    @Autowired
    McatMapper mcatMapper;

    public int select(String mcatName) {
        return mcatMapper.select(mcatName);
    }
}
