package com.triplus.board.mapper;

import com.triplus.board.dto.QnaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface QnaMapper
{
    public QnaDto select(int brdNum);
    public QnaDto selectPwd(QnaDto dto);
    public ArrayList<QnaDto> selectAll();
    public ArrayList<QnaDto> getPageList();
    public ArrayList<QnaDto> getReplyList();
    public ArrayList<QnaDto> getAnswerList(int brdNum);
    public int insert(QnaDto boardDto);
    public int delete(QnaDto boardDto);
    public int update(QnaDto boardDto);
}
