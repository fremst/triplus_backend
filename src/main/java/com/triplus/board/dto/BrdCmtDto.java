package com.triplus.board.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BrdCmtDto{
    private int brdCmtNum;
    private int brdNum;
    private String id;
    private String contents;

    public String getContents() {
        return contents;
    }
    public int getBrdNum() {

        return brdNum;
    }
    public String getId() {
        return id;
    }

}
