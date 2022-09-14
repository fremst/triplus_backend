package com.triplus.board.service;

import com.triplus.board.dto.PkgComDto;
import com.triplus.board.mapper.PkgComMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PkgComService {

    @Autowired
    private PkgComMapper pkgComMapper;


    public int insert(PkgComDto pkgComDto) {

        return pkgComMapper.insert(pkgComDto);

    }

    public ArrayList<PkgComDto> selectAllByOid(String oid) {

        return pkgComMapper.selectAllByOid(oid);

    }

    public int getTotPkgComCnt(String oid) {

        return pkgComMapper.getTotPkgComCnt(oid);

    }

    public int getMPkgComCnt(String oid) {

        return pkgComMapper.getMPkgComCnt(oid);

    }

    public int getFPkgComCnt(String oid) {

        return pkgComMapper.getFPkgComCnt(oid);

    }

}