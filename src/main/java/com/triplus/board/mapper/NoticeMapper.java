package com.triplus.board.mapper;


import com.triplus.board.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface NoticeMapper
{
    public NoticeDto select(int brdNum);
    public ArrayList<NoticeDto>  selectAll();
    public ArrayList<NoticeDto> getPageList();
    public int insert(NoticeDto boardDto);
    public int delete(int brdNum);
    public int update(NoticeDto boardDto);
}
