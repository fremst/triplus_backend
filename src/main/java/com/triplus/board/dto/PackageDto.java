package com.triplus.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@ToString
@AllArgsConstructor
@Data
public class PackageDto {

    private int brdNum;
    private Date sDate;
    private Date eDate;
    private String mtgPlace;
    private String region;
    private int recrtCnt;
    private String trans;
    private int adultPrice;
    private int childPrice;
    private boolean canceled;
    private boolean notified;

}
