package com.triplus.board.service;

import com.triplus.board.dto.PkgImgDto;
import com.triplus.board.mapper.PkgImgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PkgImgService {

    @Autowired
    private PkgImgMapper pkgImgMapper;

//    public int insert(PkgImgDto pkgImgDto) {
//
//        return pkgImgMapper.insert(pkgImgDto);
//
//    }
//
//    public PkgImgDto select(int pkgImgNum) {
//
//        return pkgImgMapper.select(pkgImgNum);
//
//    }

    public ArrayList<PkgImgDto> selectByBrdNum(int brdNum) {

        return pkgImgMapper.selectByBrdNum(brdNum);

    }

//    public int update(PkgImgDto pkgImgDto) {
//
//        return pkgImgMapper.update(pkgImgDto);
//
//    }
//
//    public int delete(int pkgImgNum) {
//
//        return pkgImgMapper.delete(pkgImgNum);
//
//    }

}
