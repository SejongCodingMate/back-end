package com.creativesemester.SejongCodingMate.domain.quiz.controller;

import com.creativesemester.SejongCodingMate.domain.quiz.dto.request.QuizRequestDto;
import com.creativesemester.SejongCodingMate.domain.quiz.service.QuizService;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    // 1. Quiz 생성 (POST)
    @PostMapping("/api/quiz")
    public ResponseEntity<GlobalResponseDto> createQuiz (@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                         @RequestBody QuizRequestDto quizRequestDto) {
        return quizService.createQuiz(userDetails.getMember(), quizRequestDto);
    }

    // 2. Quiz 조회 (GET)
    @GetMapping("/api/quiz/{id}")
    public ResponseEntity<GlobalResponseDto> getQuiz(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long id) {
        return quizService.getQuiz(userDetails.getMember(), id);
    }

}
