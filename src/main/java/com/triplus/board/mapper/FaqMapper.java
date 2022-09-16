package com.triplus.board.mapper;

import com.triplus.board.dto.FaqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface FaqMapper {

    public ArrayList<FaqDto> selectAll();

    public FaqDto select(int faqNum);

    public int insert(FaqDto faqDto);

    public int delete(int faqNum);

    public int update(FaqDto faqDto);
}
