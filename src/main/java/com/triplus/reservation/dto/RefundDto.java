package com.triplus.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class RefundDto {
    private String cTid;
    private String tid;
    private String msg;
    private String cancelDate;
    private String cancelTime;
}
