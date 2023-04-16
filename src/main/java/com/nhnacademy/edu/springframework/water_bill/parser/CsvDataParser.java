package com.nhnacademy.edu.springframework.water_bill.parser;

import com.nhnacademy.edu.springframework.water_bill.domain.WaterBill;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvDataParser implements DataParser {

    @Override
    public List<WaterBill> getWaterBills(File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return bufferedReader.lines()
                    .skip(1)
                    .map(WaterBill::newInstance)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public String getExtension() {
        return "csv";
    }
}
