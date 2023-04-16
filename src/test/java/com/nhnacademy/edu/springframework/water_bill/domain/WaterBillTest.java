package com.nhnacademy.edu.springframework.water_bill.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WaterBillTest {

    private WaterBill waterBill;

    @BeforeAll
    void beforeAll() {
        String line = "22, 파주시 , 대중목욕탕용 ,4,2001,9999999,1250,";
        waterBill = WaterBill.newInstance(line);
    }

    @Test
    @DisplayName("생성 테스트")
    void newInstanceTest() {
        assertEquals(22, waterBill.getOrder());
        assertEquals("파주시", waterBill.getCity());
        assertEquals("대중목욕탕용", waterBill.getSector());
        assertEquals(4, waterBill.getStage());
        assertEquals(2001, waterBill.getSectionStart());
        assertEquals(9999999, waterBill.getSectionEnd());
        assertEquals(1250, waterBill.getUnitPrice());
    }

    @Test
    @DisplayName("범위 테스트")
    void isInSectionTest() {
        assertFalse(waterBill.isInSection(2000));
        assertTrue(waterBill.isInSection(2001));
    }
}