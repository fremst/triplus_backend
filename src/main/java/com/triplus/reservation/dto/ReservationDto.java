package com.triplus.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;

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
    private Integer cpnNum;
    private String resSta;
    private Date bookDate;

}
