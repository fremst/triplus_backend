package com.triplus.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class TotSalesPerMonthDto {

    private int totSales;
    private String applMonth;
    
}
