package com.creativesemester.SejongCodingMate.domain.course.service;

import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
import com.creativesemester.SejongCodingMate.domain.chapter.repository.ChapterRepository;
import com.creativesemester.SejongCodingMate.domain.course.dto.request.CourseRequestDto;
import com.creativesemester.SejongCodingMate.domain.course.dto.response.CourseResponseDto;
import com.creativesemester.SejongCodingMate.domain.course.entity.Course;
import com.creativesemester.SejongCodingMate.domain.course.repository.CourseRepository;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final ChapterRepository chapterRepository;

    @Transactional
    public ResponseEntity<GlobalResponseDto> createCourse(Member member, CourseRequestDto courseRequestDto) {

        // 1. Create Course
        courseRepository.save(Course.of(courseRequestDto.getCourseName()));

        // 2. Return
        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.LOG_IN_SUCCESS));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<GlobalResponseDto> getCourse(Member member, Long id) {

        // 1. Get Course
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.NOT_VALID_REQUEST));
        }

        // 2. Make CourseResponseDto
        List<CourseResponseDto> courseResponseDtoList = new ArrayList<>();
        List<Chapter> chapterList = chapterRepository.findByCourseId(course.get().getId());
        for (Chapter chapter : chapterList) {
            courseResponseDtoList.add(CourseResponseDto.of(course.get().getName(), chapter.getName(), new int[]{1, 2, 3}));
        }

        // 3. Return
        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.POST_TEST_SUCCESS, courseResponseDtoList));
    }

}
