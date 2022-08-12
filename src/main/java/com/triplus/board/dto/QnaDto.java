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
@ToString
public class QnaDto extends BoardDto
{
    private long answerNum; // --글의 답글
    private String category; // 문의 카테고리
    private String tempEmail; // 임시 이메일
    private String tempPwd; // 임시 비밀번호
}
