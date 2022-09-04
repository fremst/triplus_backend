package com.triplus.chat.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.triplus.chat.dto.PacketData;
import com.triplus.chat.dto.QnaChatDto;
import com.triplus.chat.service.QnaChatService;
import lombok.Data;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;

@Data
public class ChatRoom
{
    private String owner; // 주인 ID 혹은 Token
    private boolean isToken; // 비회원 여부
    private ArrayList<WebSocketSession> userList; // 현재 참여중인 세션

    private QnaChatService qnaChatService;
    private ObjectMapper objectMapper;

    public ChatRoom(QnaChatService qnaChatService, ObjectMapper objectMapper) {
        owner = "";
        isToken = false;
        userList = new ArrayList<>();
        this.qnaChatService = qnaChatService;
        this.objectMapper = objectMapper;
    }

    public void broadcast(QnaChatDto chat) throws Exception
    {
        qnaChatService.insert(chat);
        PacketData pd = new PacketData("CHAT", objectMapper.writeValueAsString(chat));
        TextMessage tm = new TextMessage(objectMapper.writeValueAsString(pd));
        for (WebSocketSession session : userList)
        {
            System.out.println("broadcast -- " + session.getId());
            session.sendMessage(tm);
        }
    }
}
