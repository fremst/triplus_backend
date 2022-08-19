package com.triplus.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@ToString
@AllArgsConstructor
@Data
public class PackageWithBoardDto {

    private int brdNum;
    private String writerId;
    private String title;
    private String contents;
    private String tImg;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd (E)", timezone = "Asia/Seoul")
    private Date wDate;
    private long hit;
    private String published;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd (E)", timezone = "Asia/Seoul")
    private Date sDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd (E)", timezone = "Asia/Seoul")
    private Date eDate;
    private String mtgPlace;
    private String region;
    private int rcrtCnt;
    private String trans;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd (E)", timezone = "Asia/Seoul")
    private int adultPrice;
    private int childPrice;
    private boolean canceled;
    private boolean notified;

}
