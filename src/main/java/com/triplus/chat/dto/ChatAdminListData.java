package com.triplus.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatAdminListData
{
    private long chatMax = 0;
    private boolean user = false;
    private String id = "";
    private String token = "";
    private long chatCnt = 0;
}
