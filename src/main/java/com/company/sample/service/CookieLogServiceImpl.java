package com.company.sample.service;

import com.company.sample.domain.CookieDetails;
import com.company.sample.repository.CookieLogRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


public class CookieLogServiceImpl {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    private static final Function<String, CookieDetails> ROW_MAPPER = line -> CookieDetails.builder()
            .cookieId(line.split(",")[0])
            .timestamp(LocalDateTime.parse(line.split(",")[1], DATE_TIME_FORMATTER))
            .build();

    public List<String> getActiveCookieForDate(String path, String date) throws Exception {
        LocalDate dateToSearch = LocalDate.parse(date, DATE_FORMATTER);
        Map<String, Long> occurrences = CookieLogRepository.getCookies(path)
                        .map(ROW_MAPPER)
                        .dropWhile(line -> line.getTimestamp().toLocalDate().isAfter(dateToSearch))
                        .takeWhile(line -> line.getTimestamp().toLocalDate().isEqual(dateToSearch))
                        .collect(Collectors.groupingBy(line -> line.getCookieId(), Collectors.counting()));

        Long maxOccurrenceNumber = occurrences.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getValue();

        return occurrences.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .takeWhile(line -> line.getValue() == maxOccurrenceNumber)
                .map(line -> line.getKey())
                .collect(toList());
    }

    public static void main(String[] args) throws Exception {
        ValidationServiceImpl.validateFileNameArgument(args[0]);
        ValidationServiceImpl.validateDateArgument(args[1]);
        CookieLogServiceImpl cookieService = new CookieLogServiceImpl();
        List<String> activeCookies = cookieService.getActiveCookieForDate(args[0], args[1]);
        activeCookies.stream().forEach(System.out::println);
    }
}
