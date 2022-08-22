package com.triplus.board.service;

import com.triplus.board.dto.QnaDto;
import com.triplus.board.mapper.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QnaService {
    @Autowired private QnaMapper qnaMapper;

    public ArrayList<QnaDto> selectAll()
    { return qnaMapper.selectAll(); }

    public QnaDto select(int brdNum)
    { return qnaMapper.select(brdNum); }

    public QnaDto selectPwd(QnaDto dto)
    { return qnaMapper.selectPwd(dto); }

    public ArrayList<QnaDto> getPageList()
    { return qnaMapper.getPageList(); }

    public int insert(QnaDto qnaDto)
    { return qnaMapper.insert(qnaDto); }

    public int delete(QnaDto qnaDto)
    { return qnaMapper.delete(qnaDto); }

    public int update(QnaDto qnaDto)
    { return qnaMapper.update(qnaDto); }
}
