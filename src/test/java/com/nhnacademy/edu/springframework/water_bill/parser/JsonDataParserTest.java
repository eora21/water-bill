package com.nhnacademy.edu.springframework.water_bill.parser;

import com.nhnacademy.edu.springframework.water_bill.domain.WaterBill;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonDataParserTest {
    private final DataParser jsonDataParser = new JsonDataParser();

    @Test
    @DisplayName("파일 로드 테스트")
    void fileLoadTest() throws IOException {
        List<WaterBill> parse = jsonDataParser.parse("/Tariff_20220331.json");
        assertNotNull(parse);
        assertFalse(parse.isEmpty());
    }

    @Test
    @DisplayName("json 확인")
    void extensionTest() {
        assertEquals("json", jsonDataParser.getExtension());
    }
}