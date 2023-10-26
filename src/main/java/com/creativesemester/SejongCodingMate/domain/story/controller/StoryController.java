package com.creativesemester.SejongCodingMate.domain.story.controller;

import com.creativesemester.SejongCodingMate.domain.story.dto.request.SaveStoryRequestDto;
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

    // 2. 스토리 전체 조회 (GET)
    @GetMapping("/api/story")
    public ResponseEntity<GlobalResponseDto> getStoryList (@AuthenticationPrincipal UserDetailsImpl userDetails){
        return storyService.getStoryList(userDetails.getMember());
    }

    // 3. 스토리 단일 조회 (GET)
    @GetMapping("/api/story/{id}")
    public ResponseEntity<GlobalResponseDto> getStory (@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id){
        return storyService.getStory(userDetails.getMember(),id);
    }

    // 4. 스토리 저장 (POST)
    @PostMapping("/api/story/save")
    public ResponseEntity<GlobalResponseDto> saveStory (@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody SaveStoryRequestDto saveStoryRequestDto){
        return storyService.saveStory(userDetails.getMember(), saveStoryRequestDto);
    }
}
