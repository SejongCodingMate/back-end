package com.creativesemester.SejongCodingMate.domain.test;

import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    // 1. POST TEST
    @PostMapping("/api/post/test")
    public ResponseEntity<GlobalResponseDto> accessIn(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                      @RequestBody TestRequestDto testRequestDto) {
        return testService.postTest(testRequestDto, userDetails.getMember());
    }


    // 2. GET TEST

    @GetMapping("/api/get/test")
    public ResponseEntity<GlobalResponseDto> accessIn(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return testService.getTest(userDetails.getMember());
    }


}
