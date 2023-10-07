package com.creativesemester.SejongCodingMate.domain.code.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CodeRequestDto {

    @NotBlank()
    private String code;
    @NotBlank()
    private String result;

    @NotBlank()
    private Boolean isInput;
    @NotBlank()
    private Long storyId;

}
