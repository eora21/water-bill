package com.nhnacademy.edu.springframework.water_bill.parser;

import com.nhnacademy.edu.springframework.water_bill.domain.WaterBill;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public interface DataParser {

    List<WaterBill> getWaterBills(File file) throws IOException;

    default List<WaterBill> parse(String filePath) throws IOException {
        File file = getFile(filePath);
        validFile(file);
        return getWaterBills(file);
    }

    default File getFile(String filePath) {
        String realPath = Objects.requireNonNull(getClass().getResource(filePath)).getPath();
        return new File(realPath);
    }

    default void validFile(File file) {
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("경로를 다시 확인해주세요.");
        }
    }

    String getExtension();
}
