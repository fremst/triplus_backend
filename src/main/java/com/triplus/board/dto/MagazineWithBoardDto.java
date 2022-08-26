package com.triplus.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MagazineWithBoardDto {

    private String title;
    private String contents;
    private String tImg;
    private String category;

    public String getCategory() {
        return category;
    }

    public String gettImg() { return tImg; }

    public String getContents() {
        return contents;
    }

    public String getTitle() {
        return title;
    }
}
