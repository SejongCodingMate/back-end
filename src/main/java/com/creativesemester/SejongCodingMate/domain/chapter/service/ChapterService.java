package com.creativesemester.SejongCodingMate.domain.chapter.service;

import com.creativesemester.SejongCodingMate.domain.chapter.dto.request.ChapterRequestDto;
import com.creativesemester.SejongCodingMate.domain.chapter.dto.request.SaveChapterRequestDto;
import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
import com.creativesemester.SejongCodingMate.domain.chapter.repository.ChapterRepository;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.domain.member.repository.MemberRepository;
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
public class ChapterService {

    private final ChapterRepository chapterRepository;
    private final MemberRepository memberRepository;

    // 1. Chapter 생성 (POST)
    @Transactional
    public ResponseEntity<GlobalResponseDto> createChapter(Member member, ChapterRequestDto chapterRequestDto) {

        chapterRepository.save(Chapter.of(chapterRequestDto));
        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.CHAPTER_CREATE_SUCCESS));
    }

    // 2. Chapter 조회 (GET)
    @Transactional
    public ResponseEntity<GlobalResponseDto> getChapter(Member member, Long id) {

        Optional<Chapter> chapter = chapterRepository.findById(id);
        if (chapter.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ErrorType.NOT_VALID_REQUEST));
        }

        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.GET_CHAPTER_SUCCESS, chapter));

    }


    public ResponseEntity<GlobalResponseDto> saveChapter(Member member, SaveChapterRequestDto saveChapterRequestDto) {

        Optional<Chapter> chapter = chapterRepository.findById(saveChapterRequestDto.getNextChapterId());

        if (chapter.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ErrorType.CHAPTER_NOT_FOUND));
        }

        member.changeChapter(chapter.get());
        memberRepository.save(member);

        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.LOG_IN_SUCCESS));

    }
}
