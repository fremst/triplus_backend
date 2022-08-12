package com.triplus.board.mapper;

import com.triplus.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
public interface BoardMapper
{
    public ArrayList<BoardDto> select();
    public int insert(BoardDto boardDto);
    public int delete(int brdNum);
    public int update(BoardDto boardDto);
}
