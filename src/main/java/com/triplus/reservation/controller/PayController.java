package com.triplus.reservation.controller;

import com.inicis.std.util.HttpUtil;
import com.inicis.std.util.ParseUtil;
import com.inicis.std.util.SignatureUtil;
import com.triplus.reservation.dto.PcPayRequestData;
import com.triplus.reservation.dto.ReservationDto;
import com.triplus.reservation.dto.PaymentDto;
import com.triplus.reservation.service.ReservationService;
import com.triplus.reservation.service.RefundService;
import com.triplus.reservation.service.PaymentService;
import com.triplus.reservation.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/api/v1/pay")
@RestController
public class PayController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    RefundService payCancelServie;

    @PostMapping("/pcpay")
    public void PayRequest(Model model, PcPayRequestData pcPayRequestData, ReservationDto reservationDto, HttpServletResponse response) {
        reservationDto.setOid(pcPayRequestData.getOid());
        reservationService.insert(reservationDto); // DB 대신 FE로 넘겨서 store에 저장하는 것 고려
        // 패키지 동행자 DB 추가
        model.addAttribute("pay", pcPayRequestData);

        try {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE HTML>\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\"/>\n" +
                    "    <script charset=\"UTF-8\" src=\"https://stdpay.inicis.com/stdjs/INIStdPay.js\"\n" +
                    "            type=\"text/javascript\"></script>\n" +
                    "    <title>PayForm</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<form id=\"PayForm\" method=\"POST\" style=\"display: none;\">\n" +
                    "    <input type=\"hidden\" name=\"goodname\" value=\"" + pcPayRequestData.getGoodname() + "\">\n" +
                    "    <input type=\"hidden\" name=\"buyername\" value=\"" + pcPayRequestData.getBuyername() + "\">\n" +
                    "    <input type=\"hidden\" name=\"buyertel\" value=\"" + pcPayRequestData.getBuyertel() + "\">\n" +
                    "    <input type=\"hidden\" name=\"buyeremail\" value=\"" + pcPayRequestData.getBuyeremail() + "\">\n" +
                    "    <input type=\"hidden\" name=\"price\" value=\"" + pcPayRequestData.getPrice() + "\">\n" +
                    "    <input type=\"hidden\" name=\"mid\" value=\"" + pcPayRequestData.getMid() + "\">\n" +
                    "    <input type=\"hidden\" name=\"gopaymethod\" value=\"Card\">\n" +
                    "    <input type=\"hidden\" name=\"mKey\" value=\"3a9503069192f207491d4b19bd743fc249a761ed94246c8c42fed06c3cd15a33\">\n" +
                    "    <input type=\"hidden\" name=\"signature\" value=\"" + pcPayRequestData.getSignature() + "\">\n" +
                    "    <input type=\"hidden\" name=\"oid\" value=\"" + pcPayRequestData.getOid() + "\">\n" +
                    "    <input type=\"hidden\" name=\"timestamp\" value=\"" + pcPayRequestData.getTimestamp() + "\">\n" +
                    "    <input type=\"hidden\" name=\"version\" value=\"1.0\">\n" +
                    "    <input type=\"hidden\" name=\"currency\" value=\"WON\">\n" +
                    "    <input type=\"hidden\" name=\"returnUrl\" value=\"http://localhost:8082/triplus/api/v1/pay/return\">\n" +
                    "    <input type=\"hidden\" name=\"closeUrl\" value=\"http://localhost:8082/triplus/api/v1/pay/close\">\n" +
                    "\n" +
                    "    <input type=\"hidden\" name=\"payViewType\" value=\"popup\">\n" +
                    "    <input type=\"hidden\" name=\"popupUrl\" value=\"http://localhost:8082/triplus/api/v1/pay/popup\">\n" +
                    "</form>\n" +
                    "<script>\n" +
                    "    INIStdPay.pay('PayForm')\n" +
                    "</script>\n" +
                    "</body>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/return")
    public void pcReturn(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();

            Map<String, String> paramMap = new Hashtable<>();

            Enumeration elems = request.getParameterNames();
            System.out.println(elems);

            String temp = "";

            while (elems.hasMoreElements()) {
                temp = (String) elems.nextElement();
                paramMap.put(temp, request.getParameter(temp));
            }

            System.out.println("paramMap : " + paramMap);

            if ("0000".equals(paramMap.get("resultCode"))) { // 인증이 성공인 경우

                out.println("####인증성공/승인요청####");
                out.println("<br/>");
                System.out.println("####인증성공/승인요청####");

                //#####################
                // 1.전문 필드 값 설정
                //#####################

                String mid = paramMap.get("mid");                        // 가맹점 ID 수신 받은 데이터로 설정
                String signKey = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";        // 가맹점에 제공된 키(이니라이트키) (가맹점 수정후 고정) !!!절대!! 전문 데이터로 설정금지
                String timestamp = SignatureUtil.getTimestamp();                // util에 의해서 자동생성
                String charset = "UTF-8";                                    // 리턴형식[UTF-8,EUC-KR](가맹점 수정후 고정)
                String format = "JSON";                                    // 리턴형식[XML,JSON,NVP](가맹점 수정후 고정)
                String authToken = paramMap.get("authToken");                // 취소 요청 tid에 따라서 유동적(가맹점 수정후 고정)
                String authUrl = paramMap.get("authUrl");                    // 승인요청 API url(수신 받은 값으로 설정, 임의 세팅 금지)
                String netCancel = paramMap.get("netCancelUrl");                // 망취소 API url(수신 받은 값으로 설정, 임의 세팅 금지)
                String ackUrl = paramMap.get("checkAckUrl");                // 가맹점 내부 로직 처리후 최종 확인 API URL(수신 받은 값으로 설정, 임의 세팅 금지)
                String merchantData = paramMap.get("merchantData");            // 가맹점 관리데이터 수신

                //#####################
                // 2.signature 생성
                //#####################
                Map<String, String> signParam = new HashMap<>();

                signParam.put("authToken", authToken);        // 필수
                signParam.put("timestamp", timestamp);        // 필수

                // signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
                String signature = SignatureUtil.makeSignature(signParam);

                String price = "";  // 가맹점에서 최종 결제 가격 표기 (필수입력아님)

                // 1. 가맹점에서 승인시 주문번호가 변경될 경우 (선택입력) 하위 연결.
                // String oid = "";

                //#####################
                // 3.API 요청 전문 생성
                //#####################
                Map<String, String> authMap = new Hashtable<>();

                authMap.put("mid", mid);              // 필수
                authMap.put("authToken", authToken);    // 필수
                authMap.put("signature", signature);    // 필수
                authMap.put("timestamp", timestamp);    // 필수
                authMap.put("charset", charset);        // default=UTF-8
                authMap.put("format", format);          // default=XML
                authMap.put("price", price);            // 가격위변조체크기능 (선택사용)

                System.out.println("##승인요청 API 요청##");

                HttpUtil httpUtil = new HttpUtil();

                try {
                    //#####################
                    // 4.API 통신 시작
                    //#####################

                    String authResultString = "";

                    authResultString = httpUtil.processHTTP(authMap, authUrl);

                    //############################################################
                    //5.API 통신결과 처리(***가맹점 개발수정***)
                    //############################################################
                    out.println("## 승인 API 결과 ##");

                    String test = authResultString.replace(",", "&").replace(":", "=").replace("\"", "").replace(" ", "").replace("\n", "").replace("}", "").replace("{", "");

                    //out.println("<pre>"+authResultString.replaceAll("<", "&lt;").replaceAll(">", "&gt;")+"</pre>");

                    Map<String, String> resultMap;

                    resultMap = ParseUtil.parseStringToMap(test); //문자열을 MAP형식으로 파싱

                    System.out.println("resultMap == " + resultMap);
                    out.println("<pre>");
                    out.println("<table width='565' border='0' cellspacing='0' cellpadding='0'>");

                    /*************************  결제보안 강화 2016-05-18 START ****************************/
                    Map<String, String> secureMap = new HashMap<>();
                    secureMap.put("mid", mid);                                //mid
                    secureMap.put("tstamp", timestamp);                        //timestemp
                    secureMap.put("MOID", resultMap.get("MOID"));            //MOID
                    secureMap.put("TotPrice", resultMap.get("TotPrice"));        //TotPrice

                    // signature 데이터 생성
                    String secureSignature = SignatureUtil.makeSignatureAuth(secureMap);
                    /*************************  결제보안 강화 2016-05-18 END ****************************/

                    if ("0000".equals(resultMap.get("resultCode")) && secureSignature.equals(resultMap.get("authSignature"))) {    //결제보안 강화 2016-05-18
                        /*****************************************************************************
                         * 여기에 가맹점 내부 DB에 결제 결과를 반영하는 관련 프로그램 코드를 구현한다.*/
                        paymentService.insert(
                                new PaymentDto(
                                        resultMap.get("tid"),
                                        resultMap.get("MOID"),
                                        resultMap.get("payMethod"),
                                        Integer.parseInt(resultMap.get("TotPrice")),
                                        resultMap.get("applNum"),
                                        resultMap.get("applDate"),
                                        resultMap.get("applTime")
                                )
                        );
                        HashMap<String, String> map = new HashMap<>();
                        map.put("oid", resultMap.get("MOID"));
                        map.put("resSta", "예약");
                        if (reservationService.updateResSta(map) < 1) {
                            throw new Exception("예약상태 변경 실패");
                        }
                        /*[중요!] 승인내용에 이상이 없음을 확인한 뒤 가맹점 DB에 해당건이 정상처리 되었음을 반영함
                        처리중 에러 발생시 망취소를 한다.
                         ******************************************************************************/
                    } else {
                        out.println("<tr><th class='td01'><p>거래 성공 여부</p></th>");
                        out.println("<td class='td02'><p>실패</p></td></tr>");
                        out.println("<tr><th class='td01'><p>결과 코드</p></th>");
                        out.println("<td class='td02'><p>" + resultMap.get("resultCode") + "</p></td></tr>");
                        out.println("<tr><th class='td01'><p>결과 내용</p></th>");
                        out.println("<td class='td02'><p>" + resultMap.get("resultMsg") + "</p></td></tr>");

                        //결제보안키가 다른 경우
                        if (!secureSignature.equals(resultMap.get("authSignature")) && "0000".equals(resultMap.get("resultCode"))) {
                            //결과정보
                            out.println("<tr><th class='line' colspan='2'><p></p></th></tr>");
                            out.println("<tr><th class='td01'><p>결과 내용</p></th>");
                            out.println("<td class='td02'><p>* 데이터 위변조 체크 실패</p></td></tr>");

                            //망취소
                            if ("0000".equals(resultMap.get("resultCode"))) {
                                throw new Exception("데이터 위변조 체크 실패");
                            }
                        }
                    }

                    //공통 부분만
                    out.println("<tr><th class='line' colspan='2'><p></p></th></tr>");
                    out.println("<tr><th class='td01'><p>거래 번호</p></th>");
                    out.println("<td class='td02'><p>" + resultMap.get("tid") + "</p></td></tr>");
                    out.println("<tr><th class='line' colspan='2'><p></p></th></tr>");
                    out.println("<tr><th class='td01'><p>결제방법(지불수단)</p></th>");
                    out.println("<td class='td02'><p>" + resultMap.get("payMethod") + "</p></td></tr>");
                    out.println("<tr><th class='line' colspan='2'><p></p></th></tr>");
                    out.println("<tr><th class='td01'><p>결제완료금액</p></th>");
                    out.println("<td class='td02'><p>" + resultMap.get("TotPrice") + "원</p></td></tr>");
                    out.println("<tr><th class='line' colspan='2'><p></p></th></tr>");
                    out.println("<tr><th class='td01'><p>주문 번호</p></th>");
                    out.println("<td class='td02'><p>" + resultMap.get("MOID") + "</p></td></tr>");
                    out.println("<tr><th class='line' colspan='2'><p></p></th></tr>");
                    out.println("<tr><th class='td01'><p>승인날짜</p></th>");
                    out.println("<td class='td02'><p>" + resultMap.get("applDate") + "</p></td></tr>");
                    out.println("<tr><th class='line' colspan='2'><p></p></th></tr>");
                    out.println("<tr><th class='td01'><p>승인시간</p></th>");
                    out.println("<td class='td02'><p>" + resultMap.get("applTime") + "</p></td></tr>");
                    out.println("<tr><th class='line' colspan='2'><p></p></th></tr>");

                    if ("Card".equals(resultMap.get("payMethod"))) {//카드

                        int quota = Integer.parseInt(resultMap.get("CARD_Quota"));
                        if (resultMap.get("EventCode") != null) {
                            out.println("<tr><th class='line' colspan='2'><p></p></th></tr>");
                            out.println("<tr><th class='td01'><p>이벤트 코드</p></th>");
                            out.println("<td class='td02'><p>" + resultMap.get("EventCode") + "</p></td></tr>");
                        }
                        out.println("<tr><th class='line' colspan='2'><p></p></th></tr>");
                        out.println("<tr><th class='td01'><p>카드번호</p></th>");
                        out.println("<td class='td02'><p>" + resultMap.get("CARD_Num") + "</p></td></tr>");
                        out.println("<tr><th class='line' colspan='2'><p></p></th></tr>");
                        out.println("<tr><th class='td01'><p>승인번호</p></th>");
                        out.println("<td class='td02'><p>" + resultMap.get("applNum") + "</p></td></tr>");
                        out.println("<tr><th class='line' colspan='2'><p></p></th></tr>");
                        out.println("<tr><th class='td01'><p>할부기간</p></th>");
                        out.println("<td class='td02'><p>" + resultMap.get("CARD_Quota") + "</p></td></tr>");
                        out.println("<tr><th class='line' colspan='2'><p></p></th></tr>");
                        if ("1".equals(resultMap.get("CARD_Interest")) || "1".equals(resultMap.get("EventCode"))) {
                            out.println("<tr><th class='td01'><p>할부 유형</p></th>");
                            out.println("<td class='td02'><p>무이자</p></td></tr>");
                        } else if (quota > 0 && !"1".equals(resultMap.get("CARD_Interest"))) {
                            out.println("<tr><th class='td01'><p>할부 유형</p></th>");
                            out.println("<td class='td02'><p>유이자 <font color='red'> *유이자로 표시되더라도 EventCode 및 EDI에 따라 무이자 처리가 될 수 있습니다.</font></p></td></tr>");
                        }

                        if ("1".equals(resultMap.get("point"))) {
                            out.println("<td class='td02'><p></p></td></tr>");
                            out.println("<tr><th class='td01'><p>포인트 사용 여부</p></th>");
                            out.println("<td class='td02'><p>사용</p></td></tr>");
                        } else {
                            out.println("<td class='td02'><p></p></td></tr>");
                            out.println("<tr><th class='td01'><p>포인트 사용 여부</p></th>");
                            out.println("<td class='td02'><p>미사용</p></td></tr>");
                        }
                        out.println("<tr><th class='line' colspan='2'><p></p></th></tr>");
                        out.println("<tr><th class='td01'><p>카드 종류</p></th>");
                        out.println("<td class='td02'><p>" + resultMap.get("CARD_Code") + "</p></td></tr>");
                        out.println("<tr><th class='line' colspan='2'><p></p></th></tr>");
                        out.println("<tr><th class='td01'><p>카드 발급사</p></th>");
                        out.println("<td class='td02'><p>" + resultMap.get("CARD_BankCode") + "</p></td></tr>");

                    }
                    out.println("</table>");
                    out.println("<span style='padding-left : 100px;'>");
                    out.println("</span>");
                    out.println("<form name='frm' method='post'>");
                    out.println("<input type='hidden' name='tid' value='" + resultMap.get("tid") + "'/>");
                    out.println("</form>");

                    out.println("</pre>");

                    // 수신결과를 파싱후 resultCode가 "0000"이면 승인성공 이외 실패
                    // 가맹점에서 스스로 파싱후 내부 DB 처리 후 화면에 결과 표시

                    // payViewType을 popup으로 해서 결제를 하셨을 경우
                    // 내부처리후 스크립트를 이용해 opener의 화면 전환처리를 하세요

                    //throw new Exception("강제 Exception");
                } catch (Exception ex) {

                    //####################################
                    // 실패시 처리(***가맹점 개발수정***)
                    //####################################

                    //---- db 저장 실패시 등 예외처리----//
                    ex.printStackTrace();

                    //#####################
                    // 망취소 API
                    //#####################
                    String netcancelResultString = httpUtil.processHTTP(authMap, netCancel);    // 망취소 요청 API url(고정, 임의 세팅 금지)

                    out.println("## 망취소 API 결과 ##");

                    // 취소 결과 확인
                    out.println("<p>" + netcancelResultString.replaceAll("<", "&lt;").replaceAll(">", "&gt;") + "</p>");
                }

            } else { // 인증 실패 시

                out.println("<br/>");
                out.println("####인증실패####");

                out.println("<p>" + paramMap + "</p>");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/refund")
    public void pcRefund(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();

            //* JSP 경우 아래 통신, Hash 에 필요한 암호화 lib 는 별도 제공하지 않습니다. 관련 사이트 참고및 직접 구현 부탁드립니다.
            Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다//년월일시분초 14자리 포멧
            SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss");

            //step1. 요청을 위한 파라미터 설정
            String key = "ItEQKi3rY7uvDS8l"; // INIpayTest 의 INIAPI key
            String type = "Refund";
            String paymethod = "Card";
            String timestamp = fourteen_format.format(date_now);
            String clientIp = "192.0.0.1";
            String mid = "INIpayTest";
            String tid = "StdpayCARDINIpayTest20220816010732700326";  // 40byte 승인 TID 입력
            String msg = "거래취소요청";

            //Hash 암호화
            String data_hash = key + type + paymethod + timestamp + clientIp + mid + tid;
            String hashData = HashUtils.getSHA_512(data_hash); // SHA_512_Util 을 이용하여 hash암호화(해당 LIB는 직접구현 필요)
            System.out.println(hashData);

            // 전송 URL
            String APIURL = "https://iniapi.inicis.com/api/v1/refund"; // 전송 URL
            Map<Object, Object> CashReceiptsMap = new HashMap<>();

            CashReceiptsMap.put("type", type);
            CashReceiptsMap.put("paymethod", paymethod);
            CashReceiptsMap.put("timestamp", timestamp);
            CashReceiptsMap.put("clientIp", clientIp);
            CashReceiptsMap.put("mid", mid);
            CashReceiptsMap.put("tid", tid);
            CashReceiptsMap.put("msg", msg);
            CashReceiptsMap.put("hashData", hashData);

            //step2. key=value 로 post 요청

            HttpUtil httpUtil = new HttpUtil();

            try {
                String authResultString = httpUtil.processHTTP(CashReceiptsMap, APIURL);
                System.out.println(authResultString);

                //step3. 요청 결과
                out.println("<h2>" + authResultString + "</h2>");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping("/popup")
    public void pcPopup(HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\" src=\"https://stdpay.inicis.com/stdjs/INIStdPay_popup.js\" charset=\"UTF-8\"></script>");
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/close")
    public void pcClose(HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\" src=\"https://stgstdpay.inicis.com/stdjs/INIStdPay_close.js\" charset=\"UTF-8\"></script>");
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}