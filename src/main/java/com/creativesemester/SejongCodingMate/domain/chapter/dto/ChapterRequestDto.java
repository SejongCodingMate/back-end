package com.creativesemester.SejongCodingMate.domain.chapter.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ChapterRequestDto {

    @NotBlank(message = "추가하실 챕터 이름을 알려주세요.")
    private String chapterName;

    @NotBlank(message = "기존의 과목 이름을 알려주세요.")
    private String courseName;

}
