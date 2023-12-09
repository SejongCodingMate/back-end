package com.creativesemester.SejongCodingMate.domain.code.service;

import com.creativesemester.SejongCodingMate.domain.code.dto.request.CodeExecuteRequestDto;
import com.creativesemester.SejongCodingMate.domain.code.dto.request.CodeRequestDto;
import com.creativesemester.SejongCodingMate.domain.code.entity.Code;
import com.creativesemester.SejongCodingMate.domain.code.repository.CodeRepository;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.domain.member.repository.MemberRepository;
import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
import com.creativesemester.SejongCodingMate.domain.story.repository.StoryRepository;
import com.creativesemester.SejongCodingMate.global.response.ErrorType;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.SuccessType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final StoryRepository storyRepository;
    private final CodeRepository codeRepository;
    private final CompilerService compilerService;
    private final MemberRepository memberRepository;

    // 1. Code 생성
    @Transactional
    public ResponseEntity<GlobalResponseDto> createCode(Member member, CodeRequestDto codeRequestDto) {

        Optional<Story> story = storyRepository.findById(codeRequestDto.getStoryId());
        if (story.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(GlobalResponseDto.of(ErrorType.STORY_NOT_FOUND));
        }
        codeRepository.save(Code.of(codeRequestDto, story.get()));
        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.CODE_CREATE_SUCCESS));
    }

    // 2. Code 단일 조회 (GET)
    @Transactional(readOnly = true)
    public ResponseEntity<GlobalResponseDto> getCode(Member member, Long id) {

        Optional<Story> story = storyRepository.findById(id);
        if (story.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(GlobalResponseDto.of(ErrorType.STORY_NOT_FOUND));
        }

        Optional<Code> code = codeRepository.findByStory(story.get());
        if (code.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(GlobalResponseDto.of(ErrorType.CODE_NOT_FOUND));
        }

        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.GET_CODE_SUCCESS, code.get()));
    }

    @Transactional
    public ResponseEntity<GlobalResponseDto> executeCode(Member member, CodeExecuteRequestDto codeExecuteRequestDto) {

        Optional<Story> story = storyRepository.findById(codeExecuteRequestDto.getStoryId());
        if (story.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(GlobalResponseDto.of(ErrorType.STORY_NOT_FOUND));
        }

        Optional<Code> code = codeRepository.findByStory(story.get());
        if (code.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(GlobalResponseDto.of(ErrorType.CODE_NOT_FOUND));
        }


        String input = codeExecuteRequestDto.getInput();
        System.out.println("input = " + input);
        String executeCode =  "# -*- coding: utf-8 -*-\n" + codeExecuteRequestDto.getCode().replace("\\n", "\n");

        System.out.println(executeCode);


        Object[] result = compilerService.runCode(executeCode, input, code.get().getAnswer());
        if (result[0].getClass() == ErrorType.class) {
            return ResponseEntity
                    .badRequest()
                    .body(GlobalResponseDto.of((ErrorType) result[0]));
        }
        SuccessType successType = (SuccessType) result[0];
        String output = (String) result[1];

        return ResponseEntity.ok(GlobalResponseDto.of(successType, output));
    }
}
