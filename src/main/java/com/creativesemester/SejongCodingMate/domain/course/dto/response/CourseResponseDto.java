package com.creativesemester.SejongCodingMate.domain.course.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourseResponseDto {

    private String courseName;

    private String chapterName;

    private int[] problemIdList;


    public static CourseResponseDto of(String courseName, String chapterName, int[] problemIdList) {
        return CourseResponseDto.builder()
                .chapterName(chapterName)
                .courseName(courseName)
                .problemIdList(problemIdList)
                .build();
    }


}
