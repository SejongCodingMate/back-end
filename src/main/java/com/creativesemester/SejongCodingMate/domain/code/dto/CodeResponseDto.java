package com.creativesemester.SejongCodingMate.domain.code.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CodeResponseDto {
    private Double score;
    private String input;
    private String answer;

    public static CodeResponseDto of(Double score, String input, String answer) {
        return CodeResponseDto.builder()
                .score(score)
                .input(input)
                .answer(answer)
                .build();
    }
}
