package com.creativesemester.SejongCodingMate.domain.quiz.dto.reponse;

import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QuizResponseDto {

    private Long storyId;
    private Long nextStoryId;
    private List<QuizDto> quizDtoList;

    public static QuizResponseDto of(Story story, List<QuizDto> quizDtoList) {
        return QuizResponseDto.builder()
                .storyId(story.getId())
                .nextStoryId(story.getNextId())
                .quizDtoList(quizDtoList)
                .build();
    }
}
