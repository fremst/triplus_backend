package com.triplus.chat.service;

import com.triplus.chat.dto.ChatAdminListData;
import com.triplus.chat.dto.QnaChatDto;
import com.triplus.chat.mapper.QnaChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QnaChatService {
    @Autowired private QnaChatMapper qnaChatMapper;

    public ArrayList<QnaChatDto> selectById(String id)
    { return qnaChatMapper.selectByID(id); }
    public ArrayList<QnaChatDto> selectByToken(String token)
    { return qnaChatMapper.selectByToken(token); }

    public ArrayList<ChatAdminListData> countById()
    { return qnaChatMapper.countByID(); }
    public ArrayList<ChatAdminListData> countByToken()
    { return qnaChatMapper.countByToken(); }

    public int insert(QnaChatDto qnaChatDto)
    { return qnaChatMapper.insert(qnaChatDto); }
}
