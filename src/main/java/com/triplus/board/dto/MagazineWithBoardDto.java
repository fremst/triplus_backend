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
    private String writerId;
    private String title;
    private String contents;
    private byte[] tImg;
    private String category;

    public String getWriterId() { return writerId; }

    public String getCategory() {
        return category;
    }

    public byte[] getTImg() {
        return tImg; }

    public String getContents() {
        return contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setTImg(byte[] tImg) {
        this.tImg = tImg;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
