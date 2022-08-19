package com.triplus.board.service;

import com.triplus.board.dto.PackageDto;
import com.triplus.board.dto.PackageWithBoardDto;
import com.triplus.board.dto.PkgImgDto;
import com.triplus.board.mapper.PackageMapper;
import com.triplus.board.mapper.PkgComMapper;
import com.triplus.board.mapper.PkgImgMapper;
import com.triplus.reservation.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class PackageService {

    @Autowired
    private PackageMapper packageMapper;

    @Autowired
    private PkgComMapper pkgComMapper;

    @Autowired
    private PkgImgMapper pkgImgMapper;

    @Autowired
    private ReservationMapper reservationMapper;

    public PackageDto select(int brdNum) {

        return packageMapper.select(brdNum);

    }

    public ArrayList<PackageDto> selectAll() {

        return packageMapper.selectAll();

    }

    public PackageWithBoardDto selectWithBoard(int brdNum) {

        return packageMapper.selectWithBoard(brdNum);

    }

    public ArrayList<PackageWithBoardDto> selectAllWithBoard() {

        return packageMapper.selectAllWithBoard();

    }

    public ArrayList<PkgImgDto> selectByBrdNum(int brdNum) {

        return pkgImgMapper.selectByBrdNum(brdNum);

    }

    public int getRecrtTotCnt(HashMap<String, Object> cond) {

        return reservationMapper.getRcrtCnt(cond) + pkgComMapper.getRcrtCnt(cond);

    }

}