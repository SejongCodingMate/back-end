package com.creativesemester.SejongCodingMate.domain.quiz.entity;

import com.creativesemester.SejongCodingMate.domain.quiz.dto.request.QuizRequestDto;
import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "STORY_ID")
    private Story story;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long quizId;


    @Column(nullable = false)
    private Long formatId;

    public static Quiz of(QuizRequestDto quizRequestDto, Story story) {
        return Quiz.builder()
                .story(story)
                .text(quizRequestDto.getText())
                .answer(quizRequestDto.getAnswer())
                .description(quizRequestDto.getDescription())
                .quizId(quizRequestDto.getQuizId())
                .formatId(quizRequestDto.getFormatId())
                .build();
    }

}
