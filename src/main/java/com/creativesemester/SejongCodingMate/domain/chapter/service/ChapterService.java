package com.creativesemester.SejongCodingMate.domain.chapter.service;

import com.creativesemester.SejongCodingMate.domain.chapter.dto.ChapterRequestDto;
import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
import com.creativesemester.SejongCodingMate.domain.chapter.repository.ChapterRepository;
import com.creativesemester.SejongCodingMate.domain.course.entity.Course;
import com.creativesemester.SejongCodingMate.domain.course.repository.CourseRepository;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.domain.member.repository.MemberRepository;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChapterService {
    private final CourseRepository courseRepository;
    private final ChapterRepository chapterRepository;

    @Transactional
    public ResponseEntity<GlobalResponseDto> createChapter(Member member, ChapterRequestDto chapterRequestDto) {

        // 1. Role Check -> 관리자 권한인 경우에만 허가를 낼 수 있게 조정 필요

        // 2. createChapter
        Course course = courseRepository.findByName(chapterRequestDto.getCourseName());
        chapterRepository.save(Chapter.of(course, chapterRequestDto.getChapterName()));

        // 3. Return
        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.LOG_IN_SUCCESS));

    }

    @Transactional
    public ResponseEntity<GlobalResponseDto> getChapter(Member member, Long id) {

        Optional <Chapter> chapter = chapterRepository.findById(id);
        if(chapter.isEmpty()){
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.NOT_VALID_REQUEST));
        }

        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.LOG_IN_SUCCESS,chapter));

    }


}
