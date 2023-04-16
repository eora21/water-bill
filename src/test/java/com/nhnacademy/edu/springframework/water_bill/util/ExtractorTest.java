package com.nhnacademy.edu.springframework.water_bill.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ExtractorTest {

    @ParameterizedTest
    @DisplayName("확장자 통과 테스트")
    @CsvSource({".csv, csv", ".json, json"})
    void loadSuccessTest(String ext, String answer) {
        assertEquals(answer, Extractor.extractExtension(ext));
    }
}