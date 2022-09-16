package com.triplus.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;

@CrossOrigin("*")
@RestController
public class SendMailController {
    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "/api/sendmail", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> sendMail(String email) throws Exception {
    //http://localhost:8082/triplus/api/sendmail?email=dlrkdwnszz@gmail.com
        int rnd = (int)(Math.random() * 1000000);

        String subject = "요청하신 인증번호를 알려드립니다";
        String content = "인증번호를 인증번호 입력창에 입력해 주세요. \n" +
                         "인증번호 : "+rnd;

        String from = "dlrkdwnszz@naver.com";
        //String to = "dlrkdwnszz@gmail.com";

        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");


            mailHelper.setFrom(from);

            mailHelper.setTo(email);           //파라미터로  user 이메일
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            mailSender.send(mail);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(content);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("rnd", rnd);    // 메일에 보낸 인증번호를 응답해서 일치여부 확인
        return map;


    }

}

