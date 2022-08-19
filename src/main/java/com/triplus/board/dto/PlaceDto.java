package com.triplus.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceDto {
    private int brdNum;
    private int mcatNum;
    private int scatNum;
    private String region;
    private String addr;
    private String tel;
    private int mapx;
    private int mapy;
    private String url;
}
