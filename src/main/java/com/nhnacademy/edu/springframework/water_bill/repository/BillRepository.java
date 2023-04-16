package com.nhnacademy.edu.springframework.water_bill.repository;

import com.nhnacademy.edu.springframework.water_bill.domain.WaterBill;

import java.io.IOException;
import java.util.List;

public interface BillRepository {
    void load(String path) throws IOException;

    List<WaterBill> findChargesBasedOnUsage(int usage);

    default void validWaterBills() {
        List<WaterBill> waterBills = getWaterBills();

        if (waterBills == null || waterBills.isEmpty()) {
            throw new IllegalStateException("파일이 로드되지 않았습니다.");
        }
    }

    List<WaterBill> getWaterBills();
}
