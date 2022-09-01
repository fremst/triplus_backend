package com.triplus.schedule.dto;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ScheduleDto {

    private int skdNum;
    private Date sDate;
    private Date eDate;
    private String destination;

}
