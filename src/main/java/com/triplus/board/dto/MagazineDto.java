package com.triplus.board.dto;

import lombok.*;

import java.sql.Date;

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

    public String getCategory() {
        return category;
    }
}
