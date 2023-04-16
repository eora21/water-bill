package com.nhnacademy.edu.springframework.water_bill.service;

import com.nhnacademy.edu.springframework.water_bill.repository.BillRepository;
import com.nhnacademy.edu.springframework.water_bill.repository.BillRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BillServiceImplTest {

    @InjectMocks
    private BillServiceImpl billServiceImpl;

    @Mock
    private BillRepository billRepository;

    @BeforeAll
    void beforeAll() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("계산 확인")
    void calculateTest() {
        Mockito.when(billRepository.findChargesBasedOnUsage(Mockito.anyInt()))
                .thenReturn(Collections.emptyList());

        assertEquals(0, billServiceImpl.calculate(1000).size());
    }

}