package com.triplus.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class TotSalesDto {

    private String oid;
    private String bookerGender;
    private int totSales;

}