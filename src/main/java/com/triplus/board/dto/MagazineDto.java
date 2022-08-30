package com.triplus.board.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class MagazineDto extends BoardDto {
    private String category;

    public MagazineDto(BoardDto board, String category){
        super(board.getBrdNum(), board.getWriterId(),
                board.getTitle(), board.getContents(), board.getTImg(),
                board.getWDate(), board.getHit(), board.isPublished());
        this.category= category;
    }
}
