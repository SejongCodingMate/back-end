package com.creativesemester.SejongCodingMate.domain.code.service;


import com.creativesemester.SejongCodingMate.domain.code.dto.CodeRequestDto;
import com.creativesemester.SejongCodingMate.domain.code.dto.CodeResponseDto;
import com.creativesemester.SejongCodingMate.domain.code.entity.Code;
import com.creativesemester.SejongCodingMate.domain.code.repository.CodeRepository;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.domain.problem.entity.Problem;
import com.creativesemester.SejongCodingMate.domain.problem.repository.ProblemRepository;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class CodeService {

    private final CodeRepository codeRepository;
    private final ProblemRepository problemRepository;
    private final CompilerService compilerService;

    private static final Logger logger = LoggerFactory.getLogger(CodeService.class);
    @Transactional
    public ResponseEntity<GlobalResponseDto> executeCode(Member member, CodeRequestDto codeRequestDto) {

        // 1. Get Problem by ProblemId
        Optional<Problem> problem = problemRepository.findById(codeRequestDto.getProblemId());

        if (problem.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.PROBLEM_NOT_FOUND));
        }

        // 2. Run Code
        String code = codeRequestDto.getCode();
        String language = codeRequestDto.getLanguage();
        logger.debug(code);
        logger.debug(language);
        Map<String, String> testCases = problem.get().getTestCases();
        Object[] result = null;

        if ("C".equals(language)) {
            result = compilerService.runCCode(code, testCases);
        } else if ("Python".equals(language)) {
            result = compilerService.runPythonCode(code, testCases);
        }

        // 3. Return Result of Run Code
        ResponseCode responseCode = (ResponseCode) result[0];
        String message = responseCode.name();

        Double score = ((Number) result[1]).doubleValue() / testCases.size() * 100;
        String input = (String) result[2];
        String answer = (String) result[3];

        if (responseCode != ResponseCode.CODE_EXCEPTION) { // 서버 오류는 DB에 Code 저장하지 않음
            codeRepository.save(Code.of(member, problem.get(), code, language, message, score));
        }

        return ResponseEntity.ok(GlobalResponseDto.of(responseCode, CodeResponseDto.of(score, input, answer)));
    }

}


