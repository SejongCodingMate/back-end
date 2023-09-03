package com.creativesemester.SejongCodingMate.domain.problem.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Getter
public class ProblemRequestDto {

    @NotBlank(message = "추가하실 문제 제목을 알려주세요.")
    private String title;

    @NotBlank(message = "추가하실 문제 내용을 알려주세요.")
    private String content;

    @NotBlank(message = "기존 챕터 이름을 알려주세요.")
    private String chapterName;

    private Map<String, String> testCases;
}
