package com.creativesemester.SejongCodingMate.domain.code.controller;

import com.creativesemester.SejongCodingMate.domain.code.dto.CodeRequestDto;
import com.creativesemester.SejongCodingMate.domain.code.service.CodeService;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    // 1. Code 실행 (POST)
    @PostMapping("/api/code/c")
    public ResponseEntity<GlobalResponseDto> executeCCode(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                          @RequestBody CodeRequestDto codeRequestDto) {
        return codeService.executeCCode(userDetails.getMember(), codeRequestDto);
    }

}
