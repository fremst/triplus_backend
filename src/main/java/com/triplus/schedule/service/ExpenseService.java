package com.triplus.schedule.service;

import com.triplus.board.dto.QnaDto;
import com.triplus.board.mapper.QnaMapper;
import com.triplus.schedule.dto.ExpenseDto;
import com.triplus.schedule.mapper.ExpenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ExpenseService {
    @Autowired private ExpenseMapper expenseMapper;

    public ExpenseDto select(int skdNum)
    { return expenseMapper.select(skdNum); }

    public int insert(ExpenseDto qnaDto)
    { return expenseMapper.insert(qnaDto); }

    public int update(ExpenseDto qnaDto)
    { return expenseMapper.update(qnaDto); }
}
