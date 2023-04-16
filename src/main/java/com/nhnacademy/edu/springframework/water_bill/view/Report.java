package com.nhnacademy.edu.springframework.water_bill.view;

import com.nhnacademy.edu.springframework.water_bill.domain.WaterBill;

import java.util.Map;

public interface Report {
    void showResult(Map<WaterBill, Long> data);
}
