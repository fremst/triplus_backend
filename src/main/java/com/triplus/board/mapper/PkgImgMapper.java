package com.triplus.board.mapper;

import com.triplus.board.dto.PkgImgDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface PkgImgMapper {

    ArrayList<PkgImgDto> selectByBrdNum(int BrdNum);
    
}