package com.triplus.board.mapper;


import com.triplus.board.dto.BrdCmtDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface BoardCmtMapper
{
    public ArrayList<BrdCmtDto> selectAll();
    public ArrayList<BrdCmtDto> select(int brdNum);
    public int insert(BrdCmtDto brdcmtDto);
    public int delete(int brdCmtNum);

}
