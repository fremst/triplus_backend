package com.triplus.board.service;




import com.triplus.board.dto.BoardDto;
import com.triplus.board.dto.MagazineDto;
import com.triplus.board.mapper.BoardMapper;
import com.triplus.board.mapper.MagazineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
public class MagazineService {
    @Autowired BoardMapper boardMapper;
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
    public int insert(MagazineDto magazineDto){
        return magazineMapper.insert(magazineDto);
    }
//    @Transactional(rollbackFor = Exception.class)
//    public int insert(MagazineDto magazineDto) throws Exception{
//
//        int brdNum=boardMapper.getNextBrdNum();
//        System.out.println(brdNum);
//        System.out.println(magazineDto.getWriterId());
//        System.out.println(magazineDto.getTitle());
//        System.out.println(magazineDto.getContents());
//        System.out.println(magazineDto.getWDate());
//        System.out.println(magazineDto.getHit());
//        System.out.println(magazineDto.isPublished());
//
//        int boardResult = boardMapper.fixedInsert(
//                new BoardDto(
//                        brdNum,
//                        "admin",
//                        magazineDto.getTitle(),
//                        magazineDto.getContents(),
//                        null,
//                        null,
//                        0,
//                        magazineDto.isPublished()
//                )
//        );
//        int magazineResult = magazineMapper.insert(magazineDto);
//
//        if(magazineResult > 0){
//            return 1;
//        }else{
//            throw new Exception("DB 오류");
//        }
//    }

    public int delete(int brdNum) {

        return magazineMapper.delete(brdNum);
    }

    public int update(MagazineDto magazineDto) {

        return magazineMapper.update(magazineDto);
    }
}
