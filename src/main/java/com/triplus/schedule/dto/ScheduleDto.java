package com.triplus.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScheduleDto {

    private int skdNum;
    private Date sDate;
    private Date eDate;
    private String destination;

}
