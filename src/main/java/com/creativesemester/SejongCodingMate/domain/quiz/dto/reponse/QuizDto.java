package com.creativesemester.SejongCodingMate.domain.quiz.dto.reponse;

import com.creativesemester.SejongCodingMate.domain.quiz.entity.Quiz;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuizDto {

    private String text;
    private String answer;
    private String description;
    private Long quizId;
    private Long formatId;

    public static QuizDto of(Quiz quiz) {
        return QuizDto.builder()
                .text(quiz.getText())
                .answer(quiz.getAnswer())
                .description(quiz.getDescription())
                .quizId(quiz.getQuizId())
                .formatId(quiz.getFormatId())
                .build();
    }
}
