package com.triplus.board.util;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateUtil {

    public String getPeriod(LocalDate fromDate, LocalDate toDate) {

        int daysBetween = getDaysBetween(fromDate, toDate);

        String nights = daysBetween > 0 ? daysBetween + "박" : "";
        String days = (daysBetween + 1) + "일";

        return nights + " " + days;

    }

    public int getDaysBetween(LocalDate fromDate, LocalDate toDate) {

        return (int) DAYS.between(fromDate, toDate);

    }

}
