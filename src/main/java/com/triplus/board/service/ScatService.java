package com.triplus.board.service;

import com.triplus.board.dto.ScatDto;
import com.triplus.board.mapper.ScatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ScatService {
    @Autowired
    ScatMapper scatMapper;

    public ScatDto select(HashMap<String,Integer> map) {
        return scatMapper.select(map);
    }

    public ScatDto selectByScatName(String scatName) {
        return scatMapper.selectByScatName(scatName);
    }


}
