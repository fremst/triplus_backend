package com.triplus.board.mapper;

import com.triplus.board.dto.PackageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface PackageMapper {

    int insert(PackageDto packageDto);

    PackageDto select(int brdNum);

    int update(PackageDto packageDto);

    ArrayList<PackageDto> selectAll();

    ArrayList<PackageDto> selectAllForAdmin();

}