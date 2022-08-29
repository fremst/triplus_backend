package com.triplus.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BrdCmtDto extends BoardDto{
    private int brdCmtNum;
    private String id;
    private String contents;

    public BrdCmtDto(BoardDto board, int brdCmtNum, String id, String contents){
        super(board.getBrdNum(), board.getWriterId(),
                board.getTitle(), board.getContents(), board.getTImg(),
                board.getWDate(), board.getHit(), board.isPublished());

        this.brdCmtNum = brdCmtNum;
        this.id = id;
        this.contents = contents;
    }
}
