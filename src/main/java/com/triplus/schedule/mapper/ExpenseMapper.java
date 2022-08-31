package com.triplus.schedule.mapper;

import com.triplus.board.dto.QnaDto;
import com.triplus.schedule.dto.ExpenseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ExpenseMapper
{
    public ExpenseDto select(int brdNum);
    public int insert(ExpenseDto boardDto);
    public int update(ExpenseDto boardDto);
}
