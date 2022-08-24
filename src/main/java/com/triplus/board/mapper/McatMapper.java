package com.triplus.board.mapper;

import com.triplus.board.dto.McatDto;

public interface McatMapper {
    McatDto select(int mcatNum);

    McatDto selectByMcatName(String mcatName);

}
