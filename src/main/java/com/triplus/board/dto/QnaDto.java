package com.triplus.board.dto;


import com.triplus.board.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QnaDto extends BoardDto
{
    private long answerNum; // --글의 답글
    private String category; // 문의 카테고리
    private String tempEmail; // 임시 이메일
    private String tempPwd; // 임시 비밀번호

    public QnaDto(BoardDto board, long answerNum, String category, String tempEmail, String tempPwd)
    {
        super(board.getBrdNum(), board.getWriterId(),
                board.getTitle(), board.getContents(), board.getTImg(),
                board.getWDate(), board.getHit(), board.isPublished());
        this.answerNum = answerNum;
        this.category = category;
        this.tempEmail = tempEmail;
        this.tempPwd = tempPwd;
    }

    public String toString()
    {
        return super.toString() + "" +
                "answerNum=" + this.answerNum +
                " / category=" + this.category +
                " / tempEmail=" + this.tempEmail +
                " / tempPwd=" + this.tempPwd;
    }
}
