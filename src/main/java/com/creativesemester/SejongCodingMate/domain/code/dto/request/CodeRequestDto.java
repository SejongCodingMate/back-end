package com.creativesemester.SejongCodingMate.domain.code.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CodeRequestDto {

    @NotBlank()
    private String code;
    @NotBlank()
    private String text;
    @NotBlank()
    private String hint;
    @NotBlank()
    private String answer;
    @NotBlank()
    private String characterImage;
    @NotBlank()
    private String itemImage;
    @NotBlank()
    private String input;

    @NotBlank()
    private Long storyId;

}
