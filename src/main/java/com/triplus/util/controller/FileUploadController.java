package com.triplus.util.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@RequestMapping("/api/util")
@CrossOrigin("*")
@RestController
public class FileUploadController {

    @Autowired
    private ServletContext servletContext;

    // 서버에 파일을 직접 저장하는 컨트롤러
    @PostMapping(value = "/fileupload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, String> insert(ArrayList<MultipartFile> files) {

        HashMap<String, String> result = new HashMap<>();

        String path = servletContext.getRealPath("resources/upload");
        System.out.println(path);

        for (MultipartFile file : files) {

            String orgfilename = file.getOriginalFilename(); // 전송된 파일명
            String savefilename = UUID.randomUUID() + "_" + orgfilename; // 중복되지 않는 저장될 파일명 만들기. UUID는 자바 클래스로 중복되지 않는 난수 추출
            try {
                
                // 파일 업로드하기
                InputStream is = file.getInputStream();
                File f = new File(path + "\\" + savefilename);
                FileOutputStream fos = new FileOutputStream(f);
                FileCopyUtils.copy(is, fos);
                is.close();
                fos.close();

                // DB에 저장하기
                long filesize = file.getSize();
                /* 여기에 DB 저장 코드 추가 */

                result.put("result", "success");

            } catch (IOException e) {

                e.printStackTrace();
                result.put("result", "success");

            }

        }

        return result;

    }

}