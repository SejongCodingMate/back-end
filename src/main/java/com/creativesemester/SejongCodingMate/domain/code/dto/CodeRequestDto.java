package com.creativesemester.SejongCodingMate.domain.code.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CodeRequestDto {

    @NotBlank(message = "코드를 입력해주세요.")
    private String code;

    @NotBlank(message = "문제 번호를 입력해주세요")
    private Long problemId;

    @NotBlank(message = "언어를 입력해주세요")
    private String language;

}
