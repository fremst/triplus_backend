package com.triplus.board.mapper;


import com.triplus.board.dto.MagazineDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface MagazineMapper
{
    public MagazineDto select(int brdNum);
    public ArrayList<MagazineDto>  selectAll();
    public int insert(MagazineDto boardDto);
    public int delete(int brdNum);
    public int update(MagazineDto boardDto);
}
