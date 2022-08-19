package com.triplus.board.service;

import com.triplus.board.dto.PlaceDto;
import com.triplus.board.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PlaceService {
    @Autowired
    private PlaceMapper placeMapper;

    public int insert(PlaceDto placeDto) {
        return placeMapper.insert(placeDto);
    }

    public int delete(int brdNum) {
        return placeMapper.delete(brdNum);
    }

    public ArrayList<PlaceDto> selectAll() {
        return placeMapper.selectAll();
    }

    public PlaceDto select(int brdNum) {
        return placeMapper.select(brdNum);
    }

    public int update(PlaceDto placeDto) {
        return placeMapper.update(placeDto);
    }
}
