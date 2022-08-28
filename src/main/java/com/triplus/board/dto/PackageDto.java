package com.triplus.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PackageDto extends BoardDto {

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

    public PackageDto(int brdNum, String writerId, String title, String contents, String tImg, Date wDate, int hit, boolean published,
                      Date sDate, Date eDate, String mtgPlace, String region, int rcrtCnt, String trans,
                      int adultPrice, int childPrice, boolean canceled, boolean notified) {

        super(brdNum, writerId, title, contents, tImg, wDate, hit, published);

        this.sDate = sDate;
        this.eDate = eDate;
        this.mtgPlace = mtgPlace;
        this.region = region;
        this.rcrtCnt = rcrtCnt;
        this.trans = trans;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.canceled = canceled;
        this.notified = notified;

    }

}
