package com.creativesemester.SejongCodingMate.domain.chapter.controller;

import com.creativesemester.SejongCodingMate.domain.chapter.dto.ChapterRequestDto;
import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
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

    @PostMapping("/api/chapter")
    public ResponseEntity<GlobalResponseDto> createChapter (@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ChapterRequestDto chapterRequestDto){
        return chapterService.createChapter(userDetails.getMember(), chapterRequestDto);
    }

    @GetMapping("/api/chapter")
    public ResponseEntity<GlobalResponseDto> getChapter (@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id){
        return chapterService.getChapter(userDetails.getMember(), id);
    }



}
