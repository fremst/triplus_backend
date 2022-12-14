package com.triplus.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.triplus.board.dto.BoardDto;
import com.triplus.board.dto.QnaDto;
import com.triplus.board.service.BoardService;
import com.triplus.board.service.QnaService;
import com.triplus.user.utils.PasswordUtils;
import com.triplus.user.utils.VerifyUtils;
import com.triplus.user.dto.UserDto;
import com.triplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin("*")
@RestController
public class QnaController {

    // properties
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private QnaService qnaService;

    @GetMapping(value = "/api/service/qna", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<QnaDto> getList() {
        ArrayList<QnaDto> list = qnaService.getPageList();
        ArrayList<QnaDto> answers = qnaService.getReplyList();
        for (QnaDto dto : list)
        {
            int answerCount = 0;
            for (QnaDto answer : answers)
                answerCount += answer.getAnswerNum() == dto.getBrdNum() ? 1 : 0;
            if (answerCount > 0)
                dto.setTitle(dto.getTitle() + " [" + answerCount + "]");
        }

        return list;
    }

    @GetMapping(value = "/api/service/qna/{brdNum}/reply", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<QnaDto> getReplyList(@PathVariable int brdNum) {
        return qnaService.getAnswerList(brdNum);
    }

    @GetMapping(value = "/api/service/qna/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public QnaDto getDetail(@PathVariable int brdNum, String id, String token) {
        QnaDto qnaDto = qnaService.select(brdNum);
        if (!qnaDto.isPublished()) {

            // ?????? ????????? ?????? ?????? (???????????? ??? ??????)
            if (token != null && !token.equalsIgnoreCase("null")) {
                // ????????? ??????
                UserDto user = VerifyUtils.checkToken(userService, id, token);
                if (user != null && user.getId().equals(qnaDto.getWriterId())) {
                    boardService.updateHit(brdNum);
                    qnaDto.setPublished(true);
                }
            }
            // ????????? ????????? ????????? ?????? ???????????? ????????? ??? ??????
            if (!qnaDto.isPublished())
                qnaDto.setContents("");
        }
        else
            boardService.updateHit(brdNum);
        return qnaDto;
    }

    @GetMapping(value = "/api/service/qna/{brdNum}/password", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> getDetailWithPwd(@PathVariable int brdNum, String pwd) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", false);
        map.put("contents", "");
        QnaDto qnaDto = new QnaDto();
        qnaDto.setBrdNum(brdNum);

        // ?????????
        qnaDto.setTempPwd(PasswordUtils.encrypt(pwd));

        try {
            qnaDto = qnaService.selectPwd(qnaDto);
            if (qnaDto != null) {
                boardService.updateHit(brdNum);
                map.put("result", true);
                map.put("article", qnaDto);
            }
        } catch (Exception e) {

            e.printStackTrace();

        }

        return map;

    }

    @PostMapping(value = "/api/service/qna", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> postWrite(
            String writerId, String token,
            int answerNum, String title, String category,
            String tempEmail, String tempPwd, String contents, boolean published) {

        System.out.println("user token = " + (token == null) + ", " + (token.length()));
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", false);
        map.put("reason", "unknown");

        // ?????? ?????? ?????????
        tempPwd = PasswordUtils.encrypt(tempPwd);

        // ?????? ????????? ?????? ?????? (???????????? ??? ??????)
        if (token != null && !token.equalsIgnoreCase("null")) {
            UserDto user = VerifyUtils.checkToken(userService, writerId, token);
            if (user == null) {
                map.put("reason", "????????? ????????? ???????????? ????????????.");
                return map;
            }
            System.out.println("user = " + user.toString());
            tempEmail = user.getEmail();
            tempPwd = user.getPwd();
        }

        System.out.println("user tempPWD 2 = " + tempPwd);

        try {
            // ?????? ???????????? ?????? ???????????? ?????? ????????? ??????
            // ????????? nextval??? ???????????? dto ??? brdNum ?????? ?????????
            int brdNum = boardService.getNextBrdNum();

            // ????????? DB ??????
            BoardDto board = new BoardDto(brdNum,
                    writerId, title, contents, new byte[]{ 0 }, null, 0, published);
            int result1 = boardService.fixedInsert(board);
            if (result1 <= 0) {
                map.put("reason", "Board Service Error");
                return map;
            }

            // ????????? DB ??????
            QnaDto qna = new QnaDto(board, answerNum, category,
                    tempEmail, tempPwd);
            qna.setBrdNum(brdNum);
            int result2 = qnaService.insert(qna);
            if (result2 <= 0) {
                map.put("reason", "QnA Service Error");
                return map;
            }
            map.put("result", result1 > 0 && result2 > 0);
            map.put("brdNum", brdNum);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("reason", "DB ??????");
        }
        return map;
    }

    @PutMapping(value = "/api/service/qna/{num}/{token}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> postUpdate(
            @PathVariable int num,
            @PathVariable String token,
            @RequestBody QnaDto dto) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", false);
        map.put("reason", "unknown");

        System.out.println(dto.getWriterId());
        System.out.println(dto.toString());

        // ?????? ?????? ?????????
        dto.setTempPwd(PasswordUtils.encrypt(dto.getTempPwd()));

        // ?????? ????????? ?????? ?????? (???????????? ??? ??????)
        if (token != null && !token.equalsIgnoreCase("null")) {
            UserDto user = VerifyUtils.checkToken(userService, dto.getWriterId(), token);
            if (user == null) {
                map.put("reason", "????????? ????????? ???????????? ????????????.");
                return map;
            }
            dto.setTempEmail(user.getEmail());
            dto.setTempPwd(user.getPwd());
        }

        BoardDto board = boardService.select(dto.getBrdNum());
        dto.setTImg(new byte[]{});
        dto.setWDate(board.getWDate());

        try {

            // ????????? DB ??????
            int result1 = boardService.update(dto);
            if (result1 <= 0) {
                map.put("reason", "Board Service Error");
                return map;
            }

            // ????????? DB ??????
            int result2 = qnaService.update(dto);
            if (result2 <= 0) {
                map.put("reason", "QnA Service Error");
                return map;
            }
            map.put("result", result1 > 0 && result2 > 0);
            map.put("brdNum", dto.getBrdNum());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("reason", "DB ??????");
        }
        return map;
    }

    @DeleteMapping(value = "/api/service/qna/{brdNum}/{pwd}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> postDelete(@PathVariable int brdNum, @PathVariable String pwd) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", false);
        map.put("reason", "unknown");

        QnaDto dto = new QnaDto();
        dto.setBrdNum(brdNum);
        // ?????????
        dto.setTempPwd(PasswordUtils.encrypt(pwd));

        try {
            // ????????? DB ?????????
            int result1 = qnaService.delete(dto);
            if (result1 <= 0) {
                map.put("reason", "??????????????? ???????????????.");
                return map;
            }
            int result2 = boardService.delete((int) brdNum);
            if (result2 <= 0) {
                map.put("reason", "BoardService Error");
                return map;
            }

            map.put("result", result1 > 0 && result2 > 0);
            map.put("brdNum", brdNum);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("reason", "DB ??????");
        }
        return map;
    }


}