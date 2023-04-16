package com.nhnacademy.edu.springframework.water_bill.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springframework.water_bill.domain.WaterBill;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class JsonDataParser implements DataParser {

    @Override
    public List<WaterBill> getWaterBills(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, new TypeReference<>() {
        });
    }

    @Override
    public String getExtension() {
        return "json";
    }
}
