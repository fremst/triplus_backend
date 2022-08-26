package com.triplus.util.controller;

import com.triplus.board.dto.PkgImgDto;
import com.triplus.board.mapper.PkgImgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@RequestMapping("/api/util")
@CrossOrigin("*")
@RestController
public class BlobUploadController {

    @Autowired
    PkgImgMapper pkgImgMapper;

    // 파일을 DB에 blob 형식으로 저장하는 컨트롤러
    @PostMapping(value = "/blobupload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> insert(ArrayList<MultipartFile> files) {

        HashMap<String, String> result = new HashMap<>();

        for (MultipartFile file : files) {

            try {

                byte[] blobFile = file.getBytes();

                /* 여기에 DB 저장 코드 추가 */
                pkgImgMapper.insert(
                        new PkgImgDto(0,
                                100,
                                blobFile)
                );

                result.put("result", "success");

            } catch (IOException e) {

                e.printStackTrace();
                result.put("result", "fail");

            }

        }

        return result;

    }

}