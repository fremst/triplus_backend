package com.triplus.board.mapper;

import com.triplus.board.dto.QnaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface QnaMapper
{
    public QnaDto select(int brdNum);
    public ArrayList<QnaDto>  selectAll();
    public ArrayList<QnaDto> getPageList();
    public int insert(QnaDto boardDto);
    public int delete(QnaDto boardDto);
    public int update(QnaDto boardDto);
}
