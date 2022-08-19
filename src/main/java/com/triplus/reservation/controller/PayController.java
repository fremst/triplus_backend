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
                    "    <input type=\"hidden\" name=\"buyername\" value=\"" + pcPayRequestData.getBookerName() + "\">\n" +
                    "    <input type=\"hidden\" name=\"buyertel\" value=\"" + pcPayRequestData.getBookerTel() + "\">\n" +
                    "    <input type=\"hidden\" name=\"buyeremail\" value=\"" + pcPayRequestData.getBookerEmail() + "\">\n" +
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
                    "    <input type=\"hidden\" name=\"payViewType\" value=\"\">\n" +
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
            String tid = "StdpayCARDINIpayTest20220819022831039848";  // 40byte 승인 TID 입력
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
            out.println("<script type=\"text/javascript\" src=\"https://stgstdpay.inicis.com/stdjs/INIStdPay_close.js \" charset=\"UTF-8\"></script>");
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}