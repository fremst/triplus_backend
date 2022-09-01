package com.triplus.board.mapper;

import com.triplus.board.dto.PkgImgDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface PkgImgMapper {

    int insert(PkgImgDto pkgImgDto);

    int deleteByBrdNum(int brdNum);

    ArrayList<PkgImgDto> selectByBrdNum(int brdNum);

}