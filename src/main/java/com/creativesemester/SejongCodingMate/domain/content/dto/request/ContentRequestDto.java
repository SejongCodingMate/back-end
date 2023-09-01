package com.creativesemester.SejongCodingMate.domain.content.dto.request;


import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ContentRequestDto {

    @NotBlank(message = "추가되는 챕터 이름을 알려주세요.")
    private String chapterName;

    @NotBlank(message = "추가되는 내용을 입력해주세요.")
    private String contents;

}
