package com.triplus.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class LikeDto {
    private int brdNum;
    private String id;
    private int likeCnt;
    private int likeNum;

    public String getId() {
        return id;
    }

    public int getBrdNum() {
        return brdNum;
    }

    public int getLikeCnt() {
        return likeCnt;
    }

    public int getLikeNum() {
        return likeNum;
    }
}
