package com.nhnacademy.edu.springframework.water_bill.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Extractor {
    public static String extractExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
