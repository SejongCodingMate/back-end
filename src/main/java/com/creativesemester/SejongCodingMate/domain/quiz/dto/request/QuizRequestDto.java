package com.creativesemester.SejongCodingMate.domain.quiz.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class QuizRequestDto {

    @NotBlank()
    private String text;
    @NotBlank()
    private String answer;
    @NotBlank()
    private String description;


    @NotBlank()
    private Long storyId;
    @NotBlank()
    private Long quizId;
    @NotBlank()
    private Long formatId;

}
