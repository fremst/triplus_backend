package com.triplus.board.mapper;

import com.triplus.board.dto.PkgComDto;
import com.triplus.board.dto.TotSalesDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface PkgComMapper {

    int insert(PkgComDto pkgComDto);

    ArrayList<PkgComDto> selectAllByOid(String oid);

    int getRcrtCnt(HashMap<String, Object> map);

    ArrayList<TotSalesDto> getTotSales();

    int getTotPkgComCnt(String oid);

    int getMPkgComCnt(String oid);

    int getFPkgComCnt(String oid);

}