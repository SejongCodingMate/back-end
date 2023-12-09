package com.creativesemester.SejongCodingMate.domain.code.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CodeExecuteRequestDto {

    @NotBlank()
    private String code;
    @NotBlank()
    private String input;

    @NotBlank()
    private Long storyId;

}
