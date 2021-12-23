package com.company.sample.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CookieLogRepository {

    public static Stream<String> getCookies(String path) throws Exception {
        try {
            return Files.lines(Paths.get(path));
        } catch (IOException e) {
            throw new Exception("Please check csv file path");
        }
    }
}
