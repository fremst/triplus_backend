package com.triplus.reservation.controller;

import com.triplus.board.dto.PkgComDto;
import com.triplus.board.dto.PkgComInfo;
import com.triplus.board.service.PkgComService;
import com.triplus.reservation.dto.PcPayRequestData;
import com.triplus.reservation.dto.ReservationDto;
import com.triplus.reservation.service.ReservationService;
import com.triplus.reservation.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequestMapping("/api/v1/pay")
@RestController
public class PayController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    PkgComService pkgComService;

    @Autowired
    PaymentService paymentService;

    @PostMapping("/pcpay")
    public void PayRequest(Model model, PcPayRequestData pcPayRequestData, ReservationDto reservationDto, HttpServletResponse response,
                           PkgComInfo pkgComInfo) {

        reservationDto.setOid(pcPayRequestData.getOid());
        reservationDto.setResSta("결제전");

        reservationService.insert(reservationDto); // DB 대신 FE로 넘겨서 store에 저장하는 것 고려
        model.addAttribute("pay", pcPayRequestData);

        System.out.println(pcPayRequestData);

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
                    "    <input type=\"hidden\" name=\"goodname\" value=\"" + pcPayRequestData.getGoodName() + "\">\n" +
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
                    "    <input type=\"hidden\" name=\"closeUrl\" value=\"http://localhost:8082/triplus/api/v1/pay/close/"+reservationDto.getBrdNum()+"\">\n" +
                    "\n" +
                    "    <input type=\"hidden\" name=\"payViewType\" value=\"\">\n" +
                    "    <input type=\"hidden\" name=\"popupUrl\" value=\"http://localhost:8082/triplus/api/v1/pay/popup\">\n" +
                    "</form>\n" +
                    "<script>\n" +
                    "    INIStdPay.pay('PayForm')\n" +
                    "</script>\n" +
                    "</body>");

            // PKGCOM 테이블에 동행자 정보 추가
            if(pkgComInfo.getPkgComName() != null){
                for(int i=0; i<pkgComInfo.getPkgComName().length; i++){
                    pkgComService.insert(
                            new PkgComDto(
                                    0,
                                    pcPayRequestData.getOid(),
                                    pkgComInfo.getPkgComClass()[i],
                                    pkgComInfo.getPkgComName()[i],
                                    pkgComInfo.getPkgComTel()[i],
                                    pkgComInfo.getPkgComGender()[i]
                            )
                    );
                }
            }

//            PkgComDto pkgComDto = new PkgComDto(1,1,1,1,1,1);

        } catch (Exception e) {

            e.printStackTrace();

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

}