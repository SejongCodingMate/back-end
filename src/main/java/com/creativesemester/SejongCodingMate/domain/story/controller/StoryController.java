package com.creativesemester.SejongCodingMate.domain.story.controller;

import com.creativesemester.SejongCodingMate.domain.story.dto.request.StoryRequestDto;
import com.creativesemester.SejongCodingMate.domain.story.service.StoryService;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoryController {

    private final StoryService storyService;

    // 1. 스토리 생성 (POST)
    @PostMapping("/api/story")
    public ResponseEntity<GlobalResponseDto> createStory (@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody StoryRequestDto storyRequestDto){
        return storyService.createStory(userDetails.getMember(), storyRequestDto);
    }

    // 2. 스토리 조회 (GET)
    @GetMapping("/api/story")
    public ResponseEntity<GlobalResponseDto> getStory (@AuthenticationPrincipal UserDetailsImpl userDetails){
        return storyService.getStory(userDetails.getMember());
    }
}
