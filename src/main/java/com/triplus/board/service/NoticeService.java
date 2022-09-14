package com.triplus.board.service;



import com.triplus.board.dto.NoticeDto;
import com.triplus.board.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NoticeService {
    @Autowired private NoticeMapper noticeMapper;

    public ArrayList<NoticeDto> selectAll() {
        return noticeMapper.selectAll();
    }

    public NoticeDto select(int brdNum) {

        return noticeMapper.select(brdNum);
    }

    public ArrayList<NoticeDto> getPageList() {

        return noticeMapper.getPageList();
    }

    public int insert(NoticeDto noticeDto) {

        return noticeMapper.insert(noticeDto);
    }

    public int delete(int brdNum) {

        return noticeMapper.delete(brdNum);
    }

    public int update(NoticeDto noticeDto) {

        return noticeMapper.update(noticeDto);
    }
}
