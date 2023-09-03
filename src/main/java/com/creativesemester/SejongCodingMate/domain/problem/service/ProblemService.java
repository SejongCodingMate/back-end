package com.creativesemester.SejongCodingMate.domain.problem.service;

import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
import com.creativesemester.SejongCodingMate.domain.chapter.repository.ChapterRepository;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.domain.problem.dto.request.ProblemRequestDto;
import com.creativesemester.SejongCodingMate.domain.problem.dto.response.ProblemResponseDto;
import com.creativesemester.SejongCodingMate.domain.problem.entity.Problem;
import com.creativesemester.SejongCodingMate.domain.problem.repository.ProblemRepository;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final ChapterRepository chapterRepository;

    @Transactional
    public ResponseEntity<GlobalResponseDto> createProblem(Member member, ProblemRequestDto problemRequestDto) {

        // 1. Role Check -> 관리자 권한인 경우에만 허가를 낼 수 있게 조정 필요

        // 2. createProblem
        Chapter chapter = chapterRepository.findByName(problemRequestDto.getChapterName());
        problemRepository.save(Problem.of(chapter, problemRequestDto.getTitle(),
                problemRequestDto.getContent(), problemRequestDto.getTestCases()));

        // 3. Return
        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.PROBLEM_CREATE_SUCCESS));
    }

    @Transactional
    public ResponseEntity<GlobalResponseDto> getProblem(Member member, Long id) {

        Optional<Problem> problem = problemRepository.findById(id);
        if (problem.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.PROBLEM_NOT_FOUND));
        }

        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.PROBLEM_GET_SUCCESS,
                ProblemResponseDto.of(problem.get().getTitle(), problem.get().getContent())));
    }


}
