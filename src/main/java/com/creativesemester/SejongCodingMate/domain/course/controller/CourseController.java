package com.creativesemester.SejongCodingMate.domain.course.controller;

import com.creativesemester.SejongCodingMate.domain.course.dto.request.CourseRequestDto;
import com.creativesemester.SejongCodingMate.domain.course.service.CourseService;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/api/chapter")
    public ResponseEntity<GlobalResponseDto> createCourse (@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CourseRequestDto courseRequestDto){
        return courseService.createCourse(userDetails.getMember(), courseRequestDto);
    }

    @GetMapping("/api/chapter/{id}")
    public ResponseEntity<GlobalResponseDto> getCourse (@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id){
        return courseService.getCourse(userDetails.getMember(), id);
    }



}
