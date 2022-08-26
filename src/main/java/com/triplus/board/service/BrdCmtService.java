package com.triplus.board.service;

import com.triplus.board.dto.BrdCmtDto;
import com.triplus.board.mapper.BoardCmtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BrdCmtService {
    @Autowired
    private BoardCmtMapper boardCmtMapper;

    public ArrayList<BrdCmtDto> selectAll() {

        return boardCmtMapper.selectAll();
    }

    public ArrayList<BrdCmtDto> select(int brdNum) {

        return boardCmtMapper.select(brdNum);
    }

    public int insert(BrdCmtDto brdCmtDto) {

        return boardCmtMapper.insert(brdCmtDto);
    }

    public int delete(int brdCmtNum) {

        return boardCmtMapper.delete(brdCmtNum);
    }


}
