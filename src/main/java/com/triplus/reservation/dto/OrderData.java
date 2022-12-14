package com.triplus.reservation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Setter
@Getter
public class OrderData {

    private long timestamp;
    private String signature;
    private int price;
}
