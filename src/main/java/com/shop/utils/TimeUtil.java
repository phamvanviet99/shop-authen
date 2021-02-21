package com.shop.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static final String PATTERN = "yyyy-MM-dd";

    public static LocalDate convertToLocalDate(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(PATTERN));

    }
}
