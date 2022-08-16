package com.triplus.board.service;

import com.triplus.board.dto.PackageDto;
import com.triplus.board.dto.PackageWithBoardDto;
import com.triplus.board.mapper.PackageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PackageService {

    @Autowired
    private PackageMapper packageMapper;

    public PackageDto select(int brdNum) {

        return packageMapper.select(brdNum);

    }

    public ArrayList<PackageDto> selectAll() {

        return packageMapper.selectAll();

    }

    public PackageWithBoardDto selectWithBoard(int brdNum) {

        return packageMapper.selectWithBoard(brdNum);

    }

    public ArrayList<PackageWithBoardDto> selectAllWithBoard() {

        return packageMapper.selectAllWithBoard();

    }

}