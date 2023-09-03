package com.creativesemester.SejongCodingMate.domain.problem.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProblemResponseDto {

    private String title;
    private String content;

    public static ProblemResponseDto of(String title, String content) {
        return ProblemResponseDto.builder()
                .title(title)
                .content(content)
                .build();
    }
}
