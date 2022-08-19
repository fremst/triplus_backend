package com.triplus.board.service;

import com.triplus.board.mapper.ScatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScatService {
    @Autowired
    ScatMapper scatMapper;

    public int select(String scatName) {
        return scatMapper.select(scatName);
    }
}
