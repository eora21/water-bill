package com.nhnacademy.edu.springframework.water_bill.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.StringTokenizer;

@Getter
public class WaterBill {
    private final long order;
    private final String city;
    private final String sector;
    private final int stage;
    private final int sectionStart;
    private final int sectionEnd;
    private final int unitPrice;
    private final String basicFareForEachStage;

    public static WaterBill newInstance(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line, ",");

        if (tokenizer.countTokens() + 1 != WaterBill.class.getDeclaredFields().length) {
            throw new IllegalArgumentException();
        }

        return new WaterBill(
                Long.parseLong(tokenizer.nextToken()),
                tokenizer.nextToken().trim(),
                tokenizer.nextToken().trim(),
                Integer.parseInt(tokenizer.nextToken()),
                Integer.parseInt(tokenizer.nextToken()),
                Integer.parseInt(tokenizer.nextToken()),
                Integer.parseInt(tokenizer.nextToken()),
                ""
        );
    }

    public boolean isInSection(int usage) {
        return getSectionStart() <= usage && usage <= getSectionEnd();
    }

    @Builder
    @JsonCreator
    public WaterBill(@JsonProperty("순번") long order, @JsonProperty("지자체명") String city,
                     @JsonProperty("업종") String sector, @JsonProperty("단계") int stage,
                     @JsonProperty("구간시작(세제곱미터)") int sectionStart, @JsonProperty("구간끝(세제곱미터)") int sectionEnd,
                     @JsonProperty("구간금액(원)") int unitPrice,
                     @JsonProperty("단계별 기본요금(원)") String basicFareForEachStage) {
        this.order = order;
        this.city = city;
        this.sector = sector;
        this.stage = stage;
        this.sectionStart = sectionStart;
        this.sectionEnd = sectionEnd;
        this.unitPrice = unitPrice;
        this.basicFareForEachStage = basicFareForEachStage;
    }
}
