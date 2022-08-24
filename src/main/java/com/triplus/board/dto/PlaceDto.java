package com.triplus.board.dto;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PlaceDto extends BoardDto {

    private int mcatNum;
    private int scatNum;
    private String region;
    private String addr;
    private String tel;
    private double mapx;
    private double mapy;
    private String url;

    public PlaceDto(int brdNum, String writerId, String title, String contents, String tImg, Date wDate, int hit, boolean published,
                    int mcatNum, int scatNum, String region, String addr, String tel, double mapx, double mapy, String url) {

        super(brdNum, writerId, title, contents, tImg, wDate, hit, published);

        this.mcatNum = mcatNum;
        this.scatNum = scatNum;
        this.region = region;
        this.addr = addr;
        this.tel = tel;
        this.mapx = mapx;
        this.mapy = mapy;
        this.url = url;

    }

}
