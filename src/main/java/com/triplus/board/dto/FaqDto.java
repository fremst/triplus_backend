package com.triplus.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class FaqDto {

    private int faqNum;
    private String id;
    private String category;
    private String faqTitle;
    private String faqContent;
    
}
