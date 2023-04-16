package com.nhnacademy.edu.springframework.water_bill.view;

import com.nhnacademy.edu.springframework.water_bill.domain.WaterBill;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map;

@Slf4j
@Component
public class ReportImpl implements Report {
    @Override
    public void showResult(Map<WaterBill, Long> data) {
        data.entrySet().stream()
                .map(entry -> new WaterBillVo(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingLong(WaterBillVo::getBillTotal))
                .limit(5)
                .forEach(waterBillVo -> log.info(waterBillVo.toString()));
    }
}

@Getter
@ToString
class WaterBillVo {
    private final String city;
    private final String sector;
    private final int unitPrice;
    private final long billTotal;

    public WaterBillVo(WaterBill waterBill, long billTotal) {
        this.city = waterBill.getCity();
        this.sector = waterBill.getSector();
        this.unitPrice = waterBill.getUnitPrice();
        this.billTotal = billTotal;
    }
}
