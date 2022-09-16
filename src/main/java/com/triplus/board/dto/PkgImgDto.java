package com.triplus.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class PkgImgDto {

    private int pkgImgNum;
    private int brdNum;
    private byte[] pkgImg;
    private String pkgImgName;
    private long pkgImgSize;

}
