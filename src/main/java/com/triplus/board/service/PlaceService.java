package com.triplus.board.service;

import com.triplus.board.dto.BoardDto;
import com.triplus.board.dto.PlaceDto;
import com.triplus.board.mapper.BoardMapper;
import com.triplus.board.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class PlaceService {
    @Autowired
    private PlaceMapper placeMapper;

    @Autowired
    private BoardMapper boardMapper;

    @Transactional(rollbackFor = Exception.class)
    public int insert(PlaceDto placeDto) throws Exception {

        int brdNum = boardMapper.getNextBrdNum();
        placeDto.setBrdNum(brdNum);

        int boardResult = boardMapper.fixedInsert(
                new BoardDto(
                        placeDto.getBrdNum(),
                        placeDto.getWriterId(),
                        placeDto.getTitle(),
                        placeDto.getContents(),
                        placeDto.getTImg(),
                        null,
                        0,
                        placeDto.isPublished()
                )
        );

        int placeResult = placeMapper.insert(placeDto);

        if (boardResult > 0 && placeResult > 0) {
            return 1;
        } else {
            System.out.println("boardResult: " + boardResult);
            System.out.println("placeResult: " + placeResult);
            throw new Exception("DB 오류");
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public int delete(int brdNum) throws Exception {

        int placeResult = placeMapper.delete(brdNum);
        int boardResult = boardMapper.delete(brdNum);

        if (placeResult > 0 && boardResult > 0) {
            return 1;
        } else {
            System.out.println("placeResult: " + placeResult);
            System.out.println("boardResult: " + boardResult);
            throw new Exception("DB 오류");
        }

    }

    public ArrayList<PlaceDto> selectAll() {
        return placeMapper.selectAll();
    }

    public ArrayList<PlaceDto> selectAllById(String id) {
        return placeMapper.selectAllById(id);
    }

    public ArrayList<PlaceDto> selectAllByMcatNum(int mcatNum) {
        return placeMapper.selectAllByMcatNum(mcatNum);
    }

    public PlaceDto select(int brdNum) {
        return placeMapper.select(brdNum);
    }

    @Transactional(rollbackFor = Exception.class)
    public int update(PlaceDto placeDto) throws Exception {

        int boardResult = boardMapper.update(
                new BoardDto(
                        placeDto.getBrdNum(),
                        placeDto.getWriterId(),
                        placeDto.getTitle(),
                        placeDto.getContents(),
                        placeDto.getTImg(),
                        null,
                        placeDto.getHit(),
                        placeDto.isPublished()
                )
        );

        int placeResult = placeMapper.update(placeDto);

        if (boardResult > 0 && placeResult > 0) {
            return 1;
        } else {
            System.out.println("boardResult: " + boardResult);
            System.out.println("placeResult: " + placeResult);
            throw new Exception("DB 오류");
        }

    }

}
