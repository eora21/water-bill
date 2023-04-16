package com.nhnacademy.edu.springframework.water_bill.repository;

import com.nhnacademy.edu.springframework.water_bill.domain.WaterBill;
import com.nhnacademy.edu.springframework.water_bill.parser.CsvDataParser;
import com.nhnacademy.edu.springframework.water_bill.parser.DataParser;
import com.nhnacademy.edu.springframework.water_bill.parser.JsonDataParser;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BillRepositoryImplTest {

    BillRepository billRepository;
    DataParser[] dataParsers;

    @BeforeAll
    void beforeAll() {
        DataParser csvDataParserMock = Mockito.mock(CsvDataParser.class);
        Mockito.when(csvDataParserMock.getExtension()).thenReturn("csv");

        DataParser jsonDataParserMock = Mockito.mock(JsonDataParser.class);
        Mockito.when(jsonDataParserMock.getExtension()).thenReturn("json");

        dataParsers = new DataParser[]{csvDataParserMock, jsonDataParserMock};
        billRepository = new BillRepositoryImpl(dataParsers);
    }

    @ParameterizedTest
    @DisplayName("확장자 통과 테스트")
    @ValueSource(strings = {".csv", ".json"})
    void loadSuccessTest(String ext) {
        assertDoesNotThrow(() -> billRepository.load(ext));
    }

    @ParameterizedTest
    @DisplayName("확장자 실패 테스트")
    @ValueSource(strings = {".txt", ".html"})
    void loadFailTest(String ext) {
        assertThrows(Throwable.class, () -> billRepository.load(ext));
    }

    @Test
    @DisplayName("배열 null 테스트")
    void waterBillsNullTest() {
        ReflectionTestUtils.setField(billRepository, "waterBills", null);
        assertThrows(IllegalStateException.class, () -> billRepository.findChargesBasedOnUsage(0));
    }

    @Test
    @DisplayName("빈 배열 테스트")
    void waterBillsEmptyTest() {
        ReflectionTestUtils.setField(billRepository, "waterBills", Collections.emptyList());
        assertThrows(IllegalStateException.class, () -> billRepository.findChargesBasedOnUsage(0));
    }

    @Test
    @DisplayName("사용량 확인 테스트")
    void chargeTest() {
        WaterBill waterBillMock = Mockito.mock(WaterBill.class);
        Mockito.when(waterBillMock.isInSection(Mockito.anyInt())).thenReturn(true);
        ReflectionTestUtils.setField(billRepository, "waterBills", List.of(waterBillMock));
        assertEquals(1, billRepository.findChargesBasedOnUsage(Mockito.anyInt()).size());
    }
}