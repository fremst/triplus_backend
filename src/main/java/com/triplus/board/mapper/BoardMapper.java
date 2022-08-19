package com.triplus.board.mapper;

import com.triplus.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface BoardMapper {

    public BoardDto select(int brdNum);

    public ArrayList<BoardDto> selectAll();

    public ArrayList<BoardDto> getPageList();

    public int insert(BoardDto boardDto);

    public int delete(int brdNum);

    public int update(BoardDto boardDto);

    public int fixedInsert(BoardDto boardDto);

    public int getNextBrdNum();

    //조회수 업데이트
    public int cntUpdate(int brdNum);

    public int bPlaceUpdate(BoardDto boardDto);

}
