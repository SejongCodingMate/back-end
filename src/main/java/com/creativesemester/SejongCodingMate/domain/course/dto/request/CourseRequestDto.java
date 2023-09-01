package com.creativesemester.SejongCodingMate.domain.course.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CourseRequestDto {

    @NotBlank(message = "추가하실 과목 이름을 알려주세요.")
    private String courseName;
}
