package com.triplus.chat.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.triplus.chat.dto.ChatAdminListData;
import com.triplus.chat.dto.QnaChatDto;
import com.triplus.chat.dto.SocketAuthData;
import com.triplus.chat.dto.PacketData;
import com.triplus.chat.manager.ChatRoom;
import com.triplus.chat.service.QnaChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;

/**
 * 채팅 메시지 핸들러 클래스
 */
public class ChatHandler extends TextWebSocketHandler
{
    @Autowired
    private QnaChatService qnaChatService;

    private ObjectMapper objectMapper;
    private ArrayList<WebSocketSession> admins; // 관리자 목록
    private ArrayList<ChatRoom> chatRooms; // 현재 유지중인 방 목록

    public ChatHandler() {
        objectMapper = new ObjectMapper();
        chatRooms = new ArrayList<>();
        admins = new ArrayList<>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("[ user joined -- " + session.getId() + " ]");
        PacketData pd = new PacketData("AUTHORIZE", session.getId());
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(pd)));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("[ text message ]");
        String payload = message.getPayload();
        System.out.println("payload = " + payload);
        PacketData sd = objectMapper.readValue(payload, PacketData.class);
        switch (sd.getType())
        {
        // 인증 -> 채팅룸 만들어서 넣어주기
        case "AUTHORIZE":
            SocketAuthData sad = objectMapper.readValue(sd.getData(), SocketAuthData.class);
            joinRoom(sad, session);
            break;
        // 유저 목록 취득
        case "USERLIST":
            ArrayList<ChatAdminListData> userList = qnaChatService.countById();
            userList.forEach(chatAdminListData -> chatAdminListData.setUser(true));
            ArrayList<ChatAdminListData> tokenList = qnaChatService.countByToken();

            PacketData pd3 = new PacketData("USERLIST", objectMapper.writeValueAsString(userList));
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(pd3)));
            pd3 = new PacketData("TOKENLIST", objectMapper.writeValueAsString(tokenList));
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(pd3)));
            break;
        // 채팅방 들어가기
        case "JOIN":
            for (ChatRoom room : chatRooms) {
                room.getUserList().remove(session);
            }
            SocketAuthData sad2 = objectMapper.readValue(sd.getData(), SocketAuthData.class);
            joinRoom(sad2, session);
            break;
        // 채팅 보내기
        case "CHAT":
            System.out.println("rooms - " + chatRooms.size());
            QnaChatDto chat = objectMapper.readValue(sd.getData(), QnaChatDto.class);
            for (ChatRoom room : chatRooms) {
                if (room.getUserList().contains(session)) {
                    try {
                        room.broadcast(chat);
                    } catch (Exception e) {
                        e.printStackTrace();
                        disconnect(session);
                    }
                }
            }
            break;
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        disconnect(session);
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        disconnect(session);
    }

    public void joinRoom(SocketAuthData data, WebSocketSession session) throws Exception {
        ChatRoom chatRoom = null;
        for (ChatRoom room : chatRooms) {
            if (room.isToken() == !data.isUser() && room.getOwner().equals(data.getId())) {
                chatRoom = room;
                break;
            }
        }
        // 채팅방이 없는 경우
        if (chatRoom == null) {
            System.out.println("room not found, create new one.");
            chatRoom = new ChatRoom(qnaChatService, objectMapper);
            chatRoom.setToken(!data.isUser());
            chatRoom.setOwner(data.getId()); // 비회원이면 토큰, 아니면 아이디
            chatRooms.add(chatRoom);
        }
        chatRoom.getUserList().add(session);
        ArrayList<QnaChatDto> chatList = data.isUser() ? qnaChatService.selectById(data.getId()) : qnaChatService.selectByToken(data.getId());
        PacketData pd = new PacketData("CHATLIST", objectMapper.writeValueAsString(chatList));
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(pd)));
    }

    public void disconnect(WebSocketSession session)
    {
        System.out.println("[ user left - " + session.getId() + " ]");
        // 방 목록에서 참여자 제거
        for (ChatRoom chatRoom : chatRooms)
            chatRoom.getUserList().remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
