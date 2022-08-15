package com.triplus.board.service;

import com.triplus.board.dto.BoardDto;
import com.triplus.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    BoardDto select(int brdNum) {
        return boardMapper.select(brdNum);
    }

    ArrayList<BoardDto> selectAll() {
        return boardMapper.selectAll();
    }

    int insert(BoardDto boardDto) {
        return boardMapper.insert(boardDto);
    }

    int delete(int brdNum) {
        return boardMapper.delete(brdNum);
    }

    int update(BoardDto boardDto) {
        return boardMapper.update(boardDto);
    }

}
