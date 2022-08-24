package com.triplus.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.triplus.board.dto.BoardDto;
import com.triplus.board.dto.QnaDto;
import com.triplus.board.service.BoardService;
import com.triplus.board.service.QnaService;
import com.triplus.user.utils.VerifyUtils;
import com.triplus.user.dto.UserDto;
import com.triplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin("*")
@RestController
public class QnaController {
    // static
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    // properties
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private QnaService qnaService;

    @GetMapping(value = "/api/service/qna", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<QnaDto> getList() {
        return qnaService.getPageList();
    }

    @GetMapping(value = "/api/service/qna/{brdNum}/reply", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<QnaDto> getReplyList(@PathVariable int brdNum) {
        return qnaService.getAnswerList(brdNum);
    }

    @GetMapping(value = "/api/service/qna/{brdNum}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public QnaDto getDetail(@PathVariable int brdNum, String id, String token) {
        QnaDto qnaDto = qnaService.select(brdNum);
        if (!qnaDto.isPublished()) {

            // 유저 토큰이 있는 경우 (로그인을 한 경우)
            if (token != null) {
                // 유효성 검사
                UserDto user = VerifyUtils.checkToken(userService, id, token);
                if (user != null && user.getId().equals(qnaDto.getWriterId()))
                {
                    qnaDto.setPublished(true);
                }
            }
            // 유효성 검사에 실패한 경우 컨텐츠를 삭제한 뒤 전송
            if (!qnaDto.isPublished())
                qnaDto.setContents("");
        }
        return qnaDto;
    }
    @GetMapping(value = "/api/service/qna/{brdNum}/password", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> getDetailWithPwd(@PathVariable int brdNum, String pwd) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("result", false);
        map.put("contents", "");
        QnaDto qnaDto = new QnaDto();
        qnaDto.setBrdNum(brdNum);
        qnaDto.setTempPwd(pwd);
        try {
            qnaDto = qnaService.selectPwd(qnaDto);
            if (qnaDto != null) {
                map.put("result", true);
                map.put("article", qnaDto);
            }
        } catch (Exception e)
        { }
        return map;
    }

    @PostMapping(value = "/api/service/qna", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> postWrite(
            String writerId, String token,
            int answerNum, String title, String category,
            String tempEmail, String tempPwd, String contents, boolean isSecret) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("result", false);
        map.put("reason", "unknown");

        // 유저 토큰이 있는 경우 (로그인을 한 경우)
        if (token != null) {
            UserDto user = VerifyUtils.checkToken(userService, writerId, token);
            if (user == null)
            {
                map.put("reason", "로그인 정보가 유효하지 않습니다.");
                return map;
            }
            tempEmail = user.getEmail();
            tempPwd = user.getPwd();
        }

        try {
            // 보드 시퀀스의 가장 최신값을 얻을 방법이 없다
            // 차라리 nextval을 얻어와서 dto 간 brdNum 값을 동기화
            int brdNum = boardService.getNextBrdNum();

            // 게시글 DB 입력
            BoardDto board = new BoardDto(brdNum,
                    writerId, title, contents, "", null, 0, !isSecret);
            int result1 = boardService.fixedInsert(board);
            if (result1 <= 0) {
                map.put("reason", "Board Service Error");
                return map;
            }

            // 문의글 DB 입력
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
            map.put("reason", "DB 오류");
        }
        return map;
    }

    @PutMapping(value = "/api/service/qna/{num}/{token}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> postUpdate(
            @PathVariable int num,
            @PathVariable String token,
            @RequestBody QnaDto dto) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("result", false);
        map.put("reason", "unknown");

        System.out.println(dto.getWriterId());
        System.out.println(dto.toString());

        // 유저 토큰이 있는 경우 (로그인을 한 경우)
        if (token != null) {
            UserDto user = VerifyUtils.checkToken(userService, dto.getWriterId(), token);
            if (user == null)
            {
                map.put("reason", "로그인 정보가 유효하지 않습니다.");
                return map;
            }
            dto.setTempEmail(user.getEmail());
            dto.setTempPwd(user.getPwd());
        }
        UserDto user = userService.identifyId(dto.getWriterId());
        dto.setTempEmail(user.getEmail());
        dto.setTempPwd(user.getPwd());

        BoardDto board = boardService.select(dto.getBrdNum());
        dto.setTImg("sample.png");
        dto.setWDate(board.getWDate());

        try {

            // 게시글 DB 입력
            int result1 = boardService.update(dto);
            if (result1 <= 0) {
                map.put("reason", "Board Service Error");
                return map;
            }

            // 문의글 DB 입력
            int result2 = qnaService.update(dto);
            if (result2 <= 0) {
                map.put("reason", "QnA Service Error");
                return map;
            }
            map.put("result", result1 > 0 && result2 > 0);
            map.put("brdNum", dto.getBrdNum());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("reason", "DB 오류");
        }
        return map;
    }

    @DeleteMapping(value = "/api/service/qna/{brdNum}/{pwd}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> postDelete(@PathVariable int brdNum, @PathVariable String pwd) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("result", false);
        map.put("reason", "unknown");

        QnaDto dto = new QnaDto();
        dto.setBrdNum(brdNum);
        dto.setTempPwd(pwd);

        try {
            // 문의글 DB 지우기
            int result1 = qnaService.delete(dto);
            if (result1 <= 0) {
                map.put("reason", "비밀번호가 틀렸음");
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
            map.put("reason", "DB 오류");
        }
        return map;
    }


}
