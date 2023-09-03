package com.creativesemester.SejongCodingMate.domain.problem.controller;

import com.creativesemester.SejongCodingMate.domain.problem.dto.request.ProblemRequestDto;
import com.creativesemester.SejongCodingMate.domain.problem.service.ProblemService;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    // 1. 문제 추가 (POST)
    @PostMapping("/api/problem")
    public ResponseEntity<GlobalResponseDto> createProblem(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                           @RequestBody @Valid  ProblemRequestDto problemRequestDto) {
        return problemService.createProblem(userDetails.getMember(), problemRequestDto);
    }

    // 2. 문제 조회 (GET)
    @GetMapping("/api/problem/{id}")
    public ResponseEntity<GlobalResponseDto> getProblem(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                        @PathVariable Long id) {
        return problemService.getProblem(userDetails.getMember(), id);
    }


}
