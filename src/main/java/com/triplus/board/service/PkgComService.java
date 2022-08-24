package com.triplus.board.service;

import com.triplus.board.dto.PackageDto;
import com.triplus.board.dto.PkgComDto;
import com.triplus.board.mapper.PackageMapper;
import com.triplus.board.mapper.PkgComMapper;
import com.triplus.board.mapper.PkgImgMapper;
import com.triplus.reservation.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PkgComService {

    @Autowired
    private PackageMapper packageMapper;

    @Autowired
    private PkgComMapper pkgComMapper;

    @Autowired
    private PkgImgMapper pkgImgMapper;

    @Autowired
    private ReservationMapper reservationMapper;

    public int insert(PkgComDto pkgComDto){

        return pkgComMapper.insert(pkgComDto);

    }

    public ArrayList<PkgComDto> selectAllByOid(String oid){

        return pkgComMapper.selectAllByOid(oid);

    }


    public PackageDto select(int brdNum) {

        return packageMapper.select(brdNum);

    }

    public ArrayList<PackageDto> selectAll() {

        return packageMapper.selectAll();

    }

}