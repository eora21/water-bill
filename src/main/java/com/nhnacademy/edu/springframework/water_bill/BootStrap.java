package com.nhnacademy.edu.springframework.water_bill;

import com.nhnacademy.edu.springframework.water_bill.config.SpringConfig;
import com.nhnacademy.edu.springframework.water_bill.domain.WaterBill;
import com.nhnacademy.edu.springframework.water_bill.repository.BillRepository;
import com.nhnacademy.edu.springframework.water_bill.service.BillService;
import com.nhnacademy.edu.springframework.water_bill.view.Report;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

@Slf4j
public class BootStrap {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class)) {
            BillService billService = context.getBean(BillService.class);
            BillRepository billRepository = context.getBean(BillRepository.class);
            Report report = context.getBean(Report.class);

            billRepository.load("/Tariff_20220331.csv");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                log.info("사용량을 입력해주세요(종료: q).");
                String line = scanner.nextLine();

                if ("q".equalsIgnoreCase(line)) {
                    log.info("종료합니다.");
                    return;
                }

                Map<WaterBill, Long> calculate = billService.calculate(Integer.parseInt(line));
                report.showResult(calculate);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
