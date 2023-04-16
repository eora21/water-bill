package com.nhnacademy.edu.springframework.water_bill.service;

import com.nhnacademy.edu.springframework.water_bill.domain.WaterBill;

import java.util.Map;

public interface BillService {
    Map<WaterBill, Long> calculate(int usage);
}
