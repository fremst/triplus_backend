package com.triplus.board.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date wDate;
    private int hit;
    private boolean published;



    public BoardDto(int brdNum, Object writerId, Object title, Object contents, Object tImg, Object wDate, Object hit, Object published) {
    }

    public int getBrdNum() { return brdNum; }

    public String getTitle() { return title; }

    public String getContents() { return contents; }

    public byte[] getTImg() { return tImg; }

    public Date getWDate() { return wDate; }

    public int getHit() { return hit; }

    public String getWriterId() { return writerId; }

    public boolean isPublished() { return published; }

    public void setTImg(byte[] tImg) {
        this.tImg = tImg;
    }
    public void setBrdNum(int brdNum) {
        this.brdNum = brdNum;
    }
}
