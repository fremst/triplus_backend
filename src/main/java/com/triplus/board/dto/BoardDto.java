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
public class BoardDto {

    private int brdNum;
    private String writerId;
    private String title;
    private String contents;
    private byte[] tImg;
    private Date wDate;
    private int hit;
    private boolean published;

}
