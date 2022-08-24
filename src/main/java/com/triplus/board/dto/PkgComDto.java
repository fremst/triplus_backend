package com.triplus.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class PkgComDto {

    private int pkgComNum;
    private String oid;
    private String pkgComClass;
    private String pkgComName;
    private String pkgComTel;
    private char pkgComGender;

}
