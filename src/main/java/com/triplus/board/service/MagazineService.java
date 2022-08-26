package com.triplus.board.service;




import com.triplus.board.dto.MagazineDto;
import com.triplus.board.mapper.MagazineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class MagazineService {
    @Autowired private MagazineMapper magazineMapper;

    public ArrayList<MagazineDto> selectAll() {
        return magazineMapper.selectAll();
    }

    public MagazineDto select(int brdNum) {

        return magazineMapper.select(brdNum);
    }

    public ArrayList<MagazineDto> getPageList() {

        return magazineMapper.getPageList();
    }

    public int insert(MagazineDto magazineDto) {

        return magazineMapper.insert(magazineDto);
    }

    public int delete(int brdNum) {

        return magazineMapper.delete(brdNum);
    }

    public int update(MagazineDto magazineDto) {

        return magazineMapper.update(magazineDto);
    }
}
