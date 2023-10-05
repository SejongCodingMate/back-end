package com.creativesemester.SejongCodingMate.domain.story.dto.response;

import com.creativesemester.SejongCodingMate.domain.quiz.dto.reponse.QuizDto;
import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StoryResponseDto {
    private Long id;
    private Long nextStoryId;


    public static StoryResponseDto of(Story story, List<QuizDto> quizDtoList) {
        return StoryResponseDto.builder()
                .id(story.getId())
                .nextStoryId(story.getNextId())
                .build();
    }
}
