package com.creativesemester.SejongCodingMate.domain.chapter.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ChapterRequestDto {

    @NotBlank(message = "챕터 번호를 알려주세요.")
    private Long chapterNumber;

    @NotBlank(message = "첫번째 스토리 ID를 알려주세요.")
    private Long firstStoryId;

    @NotBlank(message = "첫번째 스토리 ID를 알려주세요.")
    private Long lastStoryId;

    @NotBlank(message = "챕터 제목을 알려주세요.")
    private String title;

    @NotBlank(message = "캐릭터 이미지 Url을 알려주세요.")
    private String url;

}
