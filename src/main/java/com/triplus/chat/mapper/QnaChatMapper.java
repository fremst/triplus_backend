package com.triplus.chat.mapper;

import com.triplus.board.dto.QnaDto;
import com.triplus.chat.dto.ChatAdminListData;
import com.triplus.chat.dto.QnaChatDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface QnaChatMapper
{
    public ArrayList<QnaChatDto> selectByID(String id);
    public ArrayList<QnaChatDto> selectByToken(String token);
    public ArrayList<ChatAdminListData> countByID();
    public ArrayList<ChatAdminListData> countByToken();
    public int insert(QnaChatDto qnaChatDto);
}
