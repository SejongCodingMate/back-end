package com.creativesemester.SejongCodingMate.domain.code.controller;

import com.creativesemester.SejongCodingMate.domain.code.dto.request.CodeExecuteRequestDto;
import com.creativesemester.SejongCodingMate.domain.code.dto.request.CodeRequestDto;
import com.creativesemester.SejongCodingMate.domain.code.service.CodeService;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    // 1. Code 생성 (POST)
    @PostMapping("/api/code")
    public ResponseEntity<GlobalResponseDto> createCode(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                        @RequestBody CodeRequestDto codeRequestDto) {
        return codeService.createCode(userDetails.getMember(), codeRequestDto);
    }

    // 2 .Code 단일 조회 (GET)
    @GetMapping("/api/code/{id}")
    public ResponseEntity<GlobalResponseDto> getCode(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long id) {
        return codeService.getCode(userDetails.getMember(), id);
    }

    // 3. Code 실행 (POST)
    @PostMapping("/api/code/execute")
    public ResponseEntity<GlobalResponseDto> executeCode(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                         @RequestBody CodeExecuteRequestDto codeExecuteRequestDto) {
        return codeService.executeCode(userDetails.getMember(), codeExecuteRequestDto);
    }
}