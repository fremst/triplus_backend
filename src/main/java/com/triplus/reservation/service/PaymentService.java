package com.triplus.reservation.service;

import com.triplus.reservation.dto.PaymentDto;
import com.triplus.reservation.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    public int insert(PaymentDto paymentDto) {

        return paymentMapper.insert(paymentDto);
        
    }

    public PaymentDto select(String tid){

        return paymentMapper.select(tid);

    }

    public PaymentDto selectByOid(String oid) {
        
        return paymentMapper.selectByOid(oid);

    }
}