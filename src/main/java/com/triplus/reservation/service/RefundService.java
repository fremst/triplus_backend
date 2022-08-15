package com.triplus.reservation.service;

import com.triplus.reservation.dto.RefundDto;
import com.triplus.reservation.mapper.RefundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefundService {
    @Autowired
    private RefundMapper refundMapper;

    public int insert(RefundDto refundDto) {

        return refundMapper.insert(refundDto);

    }
}