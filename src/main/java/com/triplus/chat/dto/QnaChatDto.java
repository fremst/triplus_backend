package com.triplus.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QnaChatDto
{
    private long chatNum;
    private String id;
    private String token;
    private String content;
    private Date date;
    private boolean managerChat;
}
