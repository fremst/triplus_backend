package com.triplus.board.service;

import com.triplus.board.dto.QnaDto;
import com.triplus.board.mapper.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class QnaService {
    @Autowired private QnaMapper qnaMapper;

    public ArrayList<QnaDto> selectAll()
    { return qnaMapper.selectAll(); }

    public QnaDto select(int brdNum)
    { return qnaMapper.select(brdNum); }

    /**
     * brdNum, tempPwd가 일치하는 게시글 취득
     */
    public QnaDto selectPwd(QnaDto dto)
    { return qnaMapper.selectPwd(dto); }

    /**
     * 페이징 처리를 위한 페이지 목록 취득
     * Contents 등 무거운 데이터를 포함하지 않음
     */
    public ArrayList<QnaDto> getPageList()
    { return qnaMapper.getPageList(); }

    /**
     * brdNum이 일치하는 글의 답변이 되는 게시글 목록 취득
     * @param brdNum 검색할 게시글 번호
     */
    public ArrayList<QnaDto> getAnswerList(int brdNum)
    { return qnaMapper.getAnswerList(brdNum); }

    public int insert(QnaDto qnaDto)
    { return qnaMapper.insert(qnaDto); }

    public int delete(QnaDto qnaDto)
    { return qnaMapper.delete(qnaDto); }

    public int update(QnaDto qnaDto)
    { return qnaMapper.update(qnaDto); }
}
