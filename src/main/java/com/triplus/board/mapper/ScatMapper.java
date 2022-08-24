package com.triplus.board.mapper;

import com.triplus.board.dto.McatDto;
import com.triplus.board.dto.ScatDto;

import java.util.HashMap;

public interface ScatMapper {
    ScatDto select(HashMap<String, Integer> map);

    ScatDto selectByScatName(String scatName);

}
