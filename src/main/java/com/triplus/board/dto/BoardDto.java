package com.triplus.board.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BoardDto
{
    private long brdNum;
    private String writerId;
    private String title;
    private String contents;
    private String tImg;
    private Date wDate;
    private long hit;
    private String published;
}
