package com.triplus.reservation.controller;

import com.triplus.reservation.dto.BestItemsDto;
import com.triplus.reservation.dto.TotSalesPerMonthDto;
import com.triplus.reservation.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RequestMapping("/api")
@CrossOrigin("*")
@RestController
public class SalesController {

    @Autowired
    SalesService salesService;

    @GetMapping(value = "/sales/bestitems/{rank}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<BestItemsDto> getBestItems(
            @PathVariable("rank") int rank
    ) {

        return salesService.getBestItems(rank);

    }

    @GetMapping(value = "/sales/gender", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Integer> getSalesPerGender() {

        return salesService.getSalesPerGender();

    }

    @GetMapping(value = "/sales/month", produces = {MediaType.APPLICATION_JSON_VALUE})
    ArrayList<TotSalesPerMonthDto> getTotSalesPerMonth() {

        return salesService.getTotSalesPerMonth();

    }

}
