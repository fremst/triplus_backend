package com.triplus.board.service;

import com.triplus.board.dto.BoardDto;
import com.triplus.board.dto.PackageDto;
import com.triplus.board.dto.PkgImgDto;
import com.triplus.board.mapper.BoardMapper;
import com.triplus.board.mapper.PackageMapper;
import com.triplus.board.mapper.PkgComMapper;
import com.triplus.board.mapper.PkgImgMapper;
import com.triplus.reservation.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class PackageService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private PackageMapper packageMapper;

    @Autowired
    private PkgComMapper pkgComMapper;

    @Autowired
    private PkgImgMapper pkgImgMapper;

    @Autowired
    private ReservationMapper reservationMapper;

    @Transactional(rollbackFor = Exception.class)
    public int insert(PackageDto packageDto, ArrayList<PkgImgDto> pkgImgDtos) throws Exception {

        int brdNum = boardMapper.getNextBrdNum();
        packageDto.setBrdNum(brdNum);
        packageDto.setPublished(true);

        int boardResult = boardMapper.fixedInsert(
                new BoardDto(
                        packageDto.getBrdNum(),
                        "admin",
                        packageDto.getTitle(),
                        packageDto.getContents(),
                        packageDto.getTImg(),
                        null,
                        0,
                        packageDto.isPublished()
                )
        );

        int packageResult = packageMapper.insert(packageDto);
        int pkgImgResult = 0;

        for(PkgImgDto pkgImgDto:pkgImgDtos){
                pkgImgDto.setBrdNum(brdNum);
                pkgImgResult = pkgImgMapper.insert(pkgImgDto);
                if(pkgImgResult < 0){
                    break;
                }
        }

        if (packageResult > 0 && pkgImgResult > 0){
            return 1;
        } else {
            System.out.println("packageResult: " + packageResult);
            System.out.println("pkgImgResult: " + pkgImgResult);
            throw new Exception("DB 오류");
        }

    }

    public PackageDto select(int brdNum) {

        return packageMapper.select(brdNum);

    }

    @Transactional(rollbackFor = Exception.class)
    public int update(PackageDto packageDto, ArrayList<PkgImgDto> pkgImgDtos) throws Exception {

        packageDto.setPublished(true);

        int boardResult = boardMapper.update(
                new BoardDto(
                        packageDto.getBrdNum(),
                        "admin",
                        packageDto.getTitle(),
                        packageDto.getContents(),
                        packageDto.getTImg(),
                        null,
                        0,
                        packageDto.isPublished()
                )
        );

        int packageResult = packageMapper.update(packageDto);
        int pkgImgDelResult = pkgImgMapper.deleteByBrdNum(packageDto.getBrdNum());
        int pkgImgResult = 0;

        if(packageResult > 0 && pkgImgDelResult > 0){

            for(PkgImgDto pkgImgDto:pkgImgDtos){
                pkgImgDto.setBrdNum(packageDto.getBrdNum());
                pkgImgResult = pkgImgMapper.insert(pkgImgDto);
                if(pkgImgResult < 0){
                    break;
                }
            }

        }

        if (packageResult > 0 && pkgImgDelResult > 0 && pkgImgResult > 0){
            return 1;

        } else {

            System.out.println("packageResult: " + packageResult);
            System.out.println("pkgImgDelResult: " + pkgImgDelResult);
            System.out.println("pkgImgResult: " + pkgImgResult);
            throw new Exception("DB 오류");

        }

    }

    public ArrayList<PackageDto> selectAll() {

        return packageMapper.selectAll();

    }

    public ArrayList<PackageDto> selectAllForAdmin() {

        return packageMapper.selectAllForAdmin();

    }

    public int getRcrtTotCnt(HashMap<String, Object> cond) {

        return reservationMapper.getRcrtCnt(cond) + pkgComMapper.getRcrtCnt(cond);

    }

}