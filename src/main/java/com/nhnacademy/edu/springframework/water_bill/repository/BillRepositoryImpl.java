package com.nhnacademy.edu.springframework.water_bill.repository;

import com.nhnacademy.edu.springframework.water_bill.domain.WaterBill;
import com.nhnacademy.edu.springframework.water_bill.parser.DataParser;
import com.nhnacademy.edu.springframework.water_bill.util.Extractor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class BillRepositoryImpl implements BillRepository {
    private final Map<String, DataParser> dataParsers = new HashMap<>();

    @Getter
    private List<WaterBill> waterBills;

    @Autowired
    public BillRepositoryImpl(DataParser[] dataParsers) {
        for (DataParser dataParser : dataParsers) {
            this.dataParsers.put(dataParser.getExtension(), dataParser);
        }
    }

    @Override
    public void load(String path) throws IOException {
        DataParser dataParser = dataParsers.get(Extractor.extractExtension(path));

        if (dataParser == null) {
            throw new IllegalArgumentException("허용되지 않은 확장자입니다.");
        }

        this.waterBills = dataParser.parse(path);
    }

    @Override
    public List<WaterBill> findChargesBasedOnUsage(int usage) {
        validWaterBills();
        return getWaterBills().stream()
                .filter(waterBill -> waterBill.isInSection(usage))
                .collect(Collectors.toList());
    }
}
