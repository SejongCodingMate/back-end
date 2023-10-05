package com.creativesemester.SejongCodingMate.domain.story.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class SaveRequestDto {

    @NotBlank()
    private Long nextStoryId;
}
