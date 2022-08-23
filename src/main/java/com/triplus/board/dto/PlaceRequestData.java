package com.triplus.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceRequestData extends PlaceDto {

    String overview;
    String firstimage;
    String homepage;
    String mcatName;
    String scatName;

}
