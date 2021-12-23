package com.company.sample.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidationServiceImpl {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void validateFileNameArgument(String arg) throws Exception {
        if (arg == null || arg.isEmpty()) {
            throw new Exception("Arguments shouldn't be empty");
        }
    }

    public static void validateDateArgument(String arg) throws Exception {
        if (arg == null || arg.isEmpty()) {
            throw new Exception("Arguments shouldn't be empty");
        }
        try {
            LocalDate.parse(arg, DATE_FORMATTER);
        } catch (Exception ex) {
            throw new Exception("Please enter the date in following format: yyyy-MM-dd ", ex);
        }
    }

}
