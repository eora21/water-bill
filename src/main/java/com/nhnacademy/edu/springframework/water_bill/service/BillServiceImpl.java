package com.nhnacademy.edu.springframework.water_bill.service;

import com.nhnacademy.edu.springframework.water_bill.domain.WaterBill;
import com.nhnacademy.edu.springframework.water_bill.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    @Override
    public Map<WaterBill, Long> calculate(int usage) {
        Map<WaterBill, Long> bills = new HashMap<>();

        billRepository.findChargesBasedOnUsage(usage).forEach(waterBill ->
                bills.put(waterBill, waterBill.getUnitPrice() * (long) usage));

        return bills;
    }
}
