package com.triplus.board.mapper;

import com.triplus.board.dto.QnaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface QnaMapper
{
    public ArrayList<QnaDto> selectList();
    public int insert(QnaDto boardDto);
    public int delete(int brdNum);
    public int update(QnaDto boardDto);
}
