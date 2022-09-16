package com.triplus.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class PaymentDto {

    private String tid;
    private String oid;
    private String payMethod;
    private int totPrice;
    private String applNum;
    private String applDate;
    private String applTime;

}
