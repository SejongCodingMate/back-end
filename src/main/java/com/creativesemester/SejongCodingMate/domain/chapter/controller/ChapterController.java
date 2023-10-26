package com.creativesemester.SejongCodingMate.domain.chapter.controller;

import com.creativesemester.SejongCodingMate.domain.chapter.dto.request.ChapterRequestDto;
import com.creativesemester.SejongCodingMate.domain.chapter.dto.request.SaveChapterRequestDto;
import com.creativesemester.SejongCodingMate.domain.chapter.service.ChapterService;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;

    // 1. Chapter 생성 (POST)
    @PostMapping("/api/chapter")
    public ResponseEntity<GlobalResponseDto> createChapter (@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ChapterRequestDto chapterRequestDto){
        return chapterService.createChapter(userDetails.getMember(), chapterRequestDto);
    }

    // 2. Chapter 조회 (GET)
    @GetMapping("/api/chapter/{id}")
    public ResponseEntity<GlobalResponseDto> getChapter (@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id){
        return chapterService.getChapter(userDetails.getMember(), id);
    }

    // 3. Chapter 저장 (POST)
    @PostMapping("/api/chapter/save")
    public ResponseEntity<GlobalResponseDto> saveChapter (@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody SaveChapterRequestDto saveChapterRequestDto) {
        return chapterService.saveChapter(userDetails.getMember(), saveChapterRequestDto);
    }


}
