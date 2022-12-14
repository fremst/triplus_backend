package com.triplus.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequestMapping("/api/v1/pay")
//@CrossOrigin("*")
@Controller
public class PayCancelController {

    @GetMapping("/close/{brdNum}")
    public void pcClose(HttpServletResponse response, @PathVariable("brdNum") int brdNum) {

        try {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script>window.top.postMessage({ msg: 'refresh'}, '*');</script>");
            out.println("<script type=\"text/javascript\" src=\"https://stgstdpay.inicis.com/stdjs/INIStdPay_close.js \" charset=\"UTF-8\"></script>");
            out.flush();

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }
}