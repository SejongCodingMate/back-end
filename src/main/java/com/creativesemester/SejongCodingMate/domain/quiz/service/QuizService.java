package com.creativesemester.SejongCodingMate.domain.quiz.service;

import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.domain.quiz.dto.reponse.QuizDto;
import com.creativesemester.SejongCodingMate.domain.quiz.dto.reponse.QuizResponseDto;
import com.creativesemester.SejongCodingMate.domain.quiz.dto.request.QuizRequestDto;
import com.creativesemester.SejongCodingMate.domain.quiz.entity.Quiz;
import com.creativesemester.SejongCodingMate.domain.quiz.repository.QuizRepository;
import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
import com.creativesemester.SejongCodingMate.domain.story.repository.StoryRepository;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.ErrorType;
import com.creativesemester.SejongCodingMate.global.response.SuccessType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final StoryRepository storyRepository;
    private final QuizRepository quizRepository;


    // 1. Quiz 생성
    @Transactional
    public ResponseEntity<GlobalResponseDto> createQuiz(Member member, QuizRequestDto quizRequestDto) {

        Optional<Story> story = storyRepository.findById(quizRequestDto.getStoryId());
        if (story.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ErrorType.STORY_NOT_FOUND));
        }
        quizRepository.save(Quiz.of(quizRequestDto, story.get()));
        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.QUIZ_CREATE_SUCCESS));
    }

    // 2.Quiz 조회
    @Transactional(readOnly = true)
    public ResponseEntity<GlobalResponseDto> getQuiz(Member member, Long id) {
        Optional<Story> story = storyRepository.findById(id);
        if (story.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ErrorType.STORY_NOT_FOUND));
        }

        List<Quiz> quizList = quizRepository.findByStory(story.get());
        if (quizList.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ErrorType.QUIZ_NOT_FOUND));
        }

        quizList.sort((q1, q2) -> (int) (q1.getQuizId() - q2.getQuizId()));
        List<QuizDto> quizDtoList = new ArrayList<>();
        for (Quiz q : quizList) {
            quizDtoList.add(QuizDto.of(q));
        }

        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.GET_QUIZ_SUCCESS,
                QuizResponseDto.of(story.get(), quizDtoList)));

    }
}
