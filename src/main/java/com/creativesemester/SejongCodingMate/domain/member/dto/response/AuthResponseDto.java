package com.creativesemester.SejongCodingMate.domain.member.dto.response;

import lombok.Getter;

@Getter
public class AuthResponseDto {
    private String studentId;
    public AuthResponseDto(String studentId) {
        this.studentId = studentId;
    }
}
