package com.triplus.board.mapper;

import com.triplus.board.dto.PlaceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface PlaceMapper {
    public int insert(PlaceDto placeDto);

    public int delete(int brdNum);

    ArrayList<PlaceDto> selectAll();

    public PlaceDto select(int brdNum);

    public int update(PlaceDto placeDto);
}
