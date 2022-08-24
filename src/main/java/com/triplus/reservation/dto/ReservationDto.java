package com.triplus.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class ReservationDto {

    private String oid;
    private int brdNum;
    private String id;
    private String bookerName;
    private String bookerEmail;
    private String bookerTel;
    private int cpnNum;
    private String resSta;
}
