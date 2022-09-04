package com.triplus.board.mapper;

import com.triplus.board.dto.PlaceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface PlaceMapper {
    int insert(PlaceDto placeDto);

    int delete(int brdNum);

    ArrayList<PlaceDto> selectAll();

    ArrayList<PlaceDto> selectAllById(String id);

    ArrayList<PlaceDto> selectAllByMcatNum(int mcatNum);

    PlaceDto select(int brdNum);

    int update(PlaceDto placeDto);
}
