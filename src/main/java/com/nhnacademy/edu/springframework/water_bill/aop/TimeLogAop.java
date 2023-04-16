package com.nhnacademy.edu.springframework.water_bill.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class TimeLogAop {

    private final BufferedWriter bufferedWriter;

    public TimeLogAop() throws IOException {
        File file = new File(Objects.requireNonNull(getClass().getResource("/elapse.log")).getPath());

        if (!file.exists()) {
            boolean isCreate = file.createNewFile();

            if (!isCreate) {
                throw new IllegalStateException("파일 생성 실패");
            }
        }

        this.bufferedWriter = new BufferedWriter(new FileWriter(file, true));
    }

    @Around("execution(* com.nhnacademy.edu.springframework.water_bill..*(..))")
    public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new Throwable();
        } finally {
            stopWatch.stop();
            bufferedWriter.append(String.format("[%s] %s.%s %dms",
                    LocalDateTime.now(),
                    pjp.getTarget().getClass().getSimpleName(),
                    pjp.getSignature().getName(),
                    stopWatch.getTotalTimeMillis()));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }
}
