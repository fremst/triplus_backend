package com.triplus.board.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class NoticeDto extends BoardDto
{
    private String category;
    private long noticeNum;

    public NoticeDto(BoardDto board, String category, long noticeNum )
    {
        super(board.getBrdNum(), board.getWriterId(),
                board.getTitle(), board.getContents(), board.getTImg(),
                board.getWDate(), board.getHit(), board.isPublished());
        this.category = category;
        this.noticeNum = noticeNum;
    }
}
