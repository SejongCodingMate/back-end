package com.creativesemester.SejongCodingMate.domain.story.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class StoryRequestDto {

    @NotBlank()
    private Long chapterId;

    @NotBlank()
    private Long nextId;

    @NotBlank()
    private Long previousId;

    @NotBlank()
    private Long formatId;

    @NotBlank()
    private String content;

    @NotBlank()
    private String backgroundImage;

}
