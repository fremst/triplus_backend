package com.triplus.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@ToString
@AllArgsConstructor
@Data
public class PackageDto {

    private int brdNum;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd (aaa)", timezone = "Asia/Seoul")
    private Date sDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd (aaa)", timezone = "Asia/Seoul")
    private Date eDate;
    private String mtgPlace;
    private String region;
    private int rcrtCnt;
    private String trans;
    private int adultPrice;
    private int childPrice;
    private boolean canceled;
    private boolean notified;

}
