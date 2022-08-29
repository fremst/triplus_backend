package com.triplus.board.dto;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PlaceRequestData extends PlaceDto {

    String overview;
    byte[] firstimage;
    String homepage;
    String mcatName;
    String scatName;

}
