package com.nhnacademy.edu.springframework.water_bill.parser;

import com.nhnacademy.edu.springframework.water_bill.domain.WaterBill;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvDataParserTest {
    private final DataParser csvDataParser = new CsvDataParser();

    @Test
    @DisplayName("파일 로드 테스트")
    void fileLoadTest() throws IOException {
        List<WaterBill> parse = csvDataParser.parse("/Tariff_20220331.csv");
        assertNotNull(parse);
        assertFalse(parse.isEmpty());
    }

    @Test
    @DisplayName("csv 확인")
    void extensionTest() {
        assertEquals("csv", csvDataParser.getExtension());
    }
}