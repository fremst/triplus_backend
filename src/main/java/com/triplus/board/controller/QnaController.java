package com.triplus.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.triplus.board.dto.BoardDto;
import com.triplus.board.dto.QnaDto;
import com.triplus.board.service.BoardService;
import com.triplus.board.service.QnaService;
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
    private BoardService boardService;
    @Autowired
    private QnaService qnaService;

    @GetMapping(value = "/api/service/qna/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<QnaDto> getList() {
        return qnaService.getPageList();
    }

    @GetMapping(value = "/api/service/qna/detail", produces = {MediaType.APPLICATION_JSON_VALUE})
    public QnaDto getDetail(int num) {
        QnaDto qnaDto = qnaService.select(num);
        if (!qnaDto.isPublished())
            qnaDto.setContents("");
        return qnaDto;
    }
    @GetMapping(value = "/api/service/qna/detail/password", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> getDetailWithPwd(int num, String pwd) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("result", false);
        map.put("contents", "");
        QnaDto qnaDto = new QnaDto();
        qnaDto.setBrdNum(num);
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

    @PostMapping(value = "/api/service/qna/write", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> postWrite(
            String writerId, int answerNum,
            String title, String category,
            String tempEmail, String tempPwd, String contents, boolean isSecret) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("result", false);
        map.put("reason", "unknown");

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

    @PostMapping(value = "/api/service/qna/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> postUpdate(
            int brdNum, String writerId, int answerNum,
            String title, String category,
            String tempEmail, String tempPwd, String contents, boolean isSecret) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("result", false);
        map.put("reason", "unknown");

        try {
            // 게시글 DB 입력
            BoardDto board = new BoardDto(brdNum,
                    writerId, title, contents, "", null, 0, !isSecret);
            int result1 = boardService.update(board);
            if (result1 <= 0) {
                map.put("reason", "Board Service Error");
                return map;
            }

            // 문의글 DB 입력
            QnaDto qna = new QnaDto(board, answerNum, category,
                    tempEmail, tempPwd);
            qna.setBrdNum(brdNum);
            int result2 = qnaService.update(qna);
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


    @PostMapping(value = "/api/service/qna/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> postDelete(int brdNum, String pwd) {
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
