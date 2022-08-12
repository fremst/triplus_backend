package com.triplus.board.service;

import com.triplus.board.dto.BoardDto;
import com.triplus.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardService {
    @Autowired private BoardMapper boardMapper;

    ArrayList<BoardDto> select()
    { return boardMapper.select(); }

    int insert(BoardDto boardDto)
    { return boardMapper.insert(boardDto); }

    int delete(int brdnum)
    { return boardMapper.delete(brdnum); }

    int update(BoardDto boardDto)
    { return boardMapper.update(boardDto); }
}
