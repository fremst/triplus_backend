package com.triplus.board.service;

import com.triplus.board.dto.McatDto;
import com.triplus.board.mapper.McatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class McatService {
    @Autowired
    McatMapper mcatMapper;

    public McatDto select(int mcatNum) {

        return mcatMapper.select(mcatNum);
        
    }

    public McatDto selectByMcatName(String mcatName) {

        return mcatMapper.selectByMcatName(mcatName);

    }

}
