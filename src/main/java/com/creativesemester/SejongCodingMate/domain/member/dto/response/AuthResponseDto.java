package com.creativesemester.SejongCodingMate.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponseDto {
    private String studentId;

    public static AuthResponseDto of(String studentId) {
        return AuthResponseDto.builder()
                .studentId(studentId)
                .build();
    }
}
