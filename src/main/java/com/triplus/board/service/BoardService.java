package com.triplus.board.service;

import com.triplus.board.dto.BoardDto;
import com.triplus.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardService {

    @Autowired private BoardMapper boardMapper;

    BoardDto select(int brdNum)
    { return boardMapper.select(brdNum); }
    
    public ArrayList<BoardDto> selectAll()
    { return boardMapper.selectAll(); }

    public ArrayList<BoardDto> getPageList()
    { return boardMapper.getPageList(); }

    // brdNum을 board_seq.nextval에 맡기고 insert
    public int insert(BoardDto boardDto)
    { return boardMapper.insert(boardDto); }

    // brdNum을 고정해서 insert
    public int fixedInsert(BoardDto boardDto)
    { return boardMapper.fixedInsert(boardDto); }

    public int delete(int brdnum)
    { return boardMapper.delete(brdnum); }

    public int update(BoardDto boardDto)
    { return boardMapper.update(boardDto); }

    public int getNextBrdNum()
    { return boardMapper.getNextBrdNum(); }
    
}