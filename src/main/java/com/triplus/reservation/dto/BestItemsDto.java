package com.triplus.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class BestItemsDto {

    private byte[] tImg;
    private String title;
    private int totSales;

}
