package com.creativesemester.SejongCodingMate.domain.dialogue.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class DialogueRequestDto {

    @NotBlank()
    private Long storyId;
    @NotBlank()
    private String speaker;
    @NotBlank()
    private String text;
    @NotBlank()
    private Long soundEffect;
    @NotBlank()
    private Long screenEffect;
    @NotBlank()
    private String characterImage;

}
