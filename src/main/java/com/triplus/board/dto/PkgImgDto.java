package com.triplus.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@ToString
@AllArgsConstructor
@Data
public class PkgImgDto {

    private int pkgImgNum;
    private int brdNum;
    private String pkgImg;

}
