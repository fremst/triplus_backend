package com.triplus.reservation.mapper;

import com.triplus.reservation.dto.RefundDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefundMapper {
    int insert(RefundDto refundDto);

//    PaymentDto select(String cTid);

//    int update(PayCancelDto paymentDto);

//    int delete(String cTid);
}