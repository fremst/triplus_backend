package com.triplus.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceDto extends BoardDto{

    private int brdNum;
    private int mcatNum;
    private int scatNum;
    private String region;
    private String addr;
    private String tel;
    private double mapx;
    private double mapy;
    private String url;

}
