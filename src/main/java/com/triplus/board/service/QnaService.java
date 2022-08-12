package com.triplus.board.service;

import com.triplus.board.dto.QnaDto;
import com.triplus.board.mapper.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QnaService {
    @Autowired private QnaMapper qnaMapper;

    public ArrayList<QnaDto> selectList()
    { return qnaMapper.selectList(); }

    public int insert(QnaDto qnaDto)
    { return qnaMapper.insert(qnaDto); }

    public int delete(int brdnum)
    { return qnaMapper.delete(brdnum); }

    public int update(QnaDto qnaDto)
    { return qnaMapper.update(qnaDto); }
}
