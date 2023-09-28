package com.creativesemester.SejongCodingMate.domain.dialogue.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class DialogueRequestDto {

    @NotBlank()
    private String speaker;
    @NotBlank()
    private String text;
    @NotBlank()
    private Long formatId;

    @NotBlank()
    private Long dialogueId;

}
