package com.creativesemester.SejongCodingMate.domain.content.controller;

import com.creativesemester.SejongCodingMate.domain.content.dto.request.ContentRequestDto;
import com.creativesemester.SejongCodingMate.domain.content.service.ContentService;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    // 1. 개념 추가 (POST)
    @PostMapping("/api/content")
    public ResponseEntity<GlobalResponseDto> createContent(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ContentRequestDto contentRequestDto){
        return contentService.createContent(userDetails.getMember(), contentRequestDto);
    }


    // 2. 개념 조회 (GET)
    @GetMapping("/api/content/{id}")
    public ResponseEntity<GlobalResponseDto> getContent (@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id){
        return contentService.getContent(userDetails.getMember(), id);
    }

}
