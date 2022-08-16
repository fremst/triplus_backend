package com.triplus.board.mapper;

import com.triplus.board.dto.PackageDto;
import com.triplus.board.dto.PackageWithBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface PackageMapper {

    PackageDto select(int brdNum);

    ArrayList<PackageDto> selectAll();

    PackageWithBoardDto selectWithBoard(int brdNum);

    ArrayList<PackageWithBoardDto> selectAllWithBoard();

}