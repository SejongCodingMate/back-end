package com.creativesemester.SejongCodingMate.domain.content.service;


import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
import com.creativesemester.SejongCodingMate.domain.chapter.repository.ChapterRepository;
import com.creativesemester.SejongCodingMate.domain.content.dto.request.ContentRequestDto;
import com.creativesemester.SejongCodingMate.domain.content.entity.Content;
import com.creativesemester.SejongCodingMate.domain.content.repository.ContentRepository;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ChapterRepository chapterRepository;
    private final ContentRepository contentRepository;

    @Transactional
    public ResponseEntity<GlobalResponseDto> createContent(Member member, ContentRequestDto contentRequestDto) {

        // 1. Create Content
        Chapter chapter = chapterRepository.findByName(contentRequestDto.getChapterName());
        contentRepository.save(Content.of(chapter, contentRequestDto.getContents()));

        // 2. Return
        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.CONTENTS_CREATE_SUCCESS));

    }

    @Transactional
    public ResponseEntity<GlobalResponseDto> getContent(Member member, Long id) {

        // 1. Get Content By ChapterId
        Optional<Content> content = contentRepository.findByChapterId(id);
        if(content.isEmpty()){
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.NOT_VALID_REQUEST));
        }

        // 2. Return
        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.GET_CONTENTS_SUCCESS,content));

    }
}
