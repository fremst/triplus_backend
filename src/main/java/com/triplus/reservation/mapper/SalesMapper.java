package com.triplus.reservation.mapper;

import com.triplus.reservation.dto.BestItemsDto;
import com.triplus.reservation.dto.TotSalesPerMonthDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface SalesMapper {

    ArrayList<BestItemsDto> getBestItems(int rank);

    ArrayList<TotSalesPerMonthDto> getTotSalesPerMonth();

}