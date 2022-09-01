package com.triplus.schedule.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SpotDto {

    private int spotNum;
    private int skdNum;
    private int day;
    private String memo;
    private int brdNum;

}
