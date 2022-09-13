package com.triplus.reservation.service;

import com.triplus.board.dto.TotSalesDto;
import com.triplus.board.mapper.PkgComMapper;
import com.triplus.reservation.dto.BestItemsDto;
import com.triplus.reservation.dto.TotSalesPerMonthDto;
import com.triplus.reservation.mapper.SalesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class SalesService {

    @Autowired
    private SalesMapper salesMapper;

    @Autowired
    private PkgComMapper pkgComMapper;

    public ArrayList<BestItemsDto> getBestItems(int rank) {

        return salesMapper.getBestItems(rank);

    }

    @Transactional(rollbackFor = Exception.class)
    public HashMap<String, Integer> getSalesPerGender() {

        HashMap<String, Integer> data = new HashMap<>();

        ArrayList<TotSalesDto> totSalesDtos = pkgComMapper.getTotSales();

        int mCnt = 0;
        int fCnt = 0;
        int mTotSales = 0;
        int fTotSales = 0;

        for (TotSalesDto dto : totSalesDtos) {

            if (dto.getBookerGender().equals("M")) {
                mCnt++;
            } else {
                fCnt++;
            }

            mCnt += pkgComMapper.getMPkgComCnt(dto.getOid());
            fCnt += pkgComMapper.getFPkgComCnt(dto.getOid());

            if (mCnt + fCnt != 0) {
                mTotSales += dto.getTotSales() * mCnt / (mCnt + fCnt);
                fTotSales += dto.getTotSales() * fCnt / (mCnt + fCnt);
            }

        }

        data.put("mCnt", mCnt);
        data.put("fCnt", fCnt);
        data.put("mTotSales", mTotSales);
        data.put("fTotSales", fTotSales);

        return data;

    }

    public ArrayList<TotSalesPerMonthDto> getTotSalesPerMonth() {

        return salesMapper.getTotSalesPerMonth();

    }

}
