package com.creativesemester.SejongCodingMate.domain.member.controller;


import com.creativesemester.SejongCodingMate.domain.member.dto.request.MemberRequestDto;
import com.creativesemester.SejongCodingMate.domain.member.service.MemberService;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;

    @PostMapping("/api/member/sign-up")
    public ResponseEntity <GlobalResponseDto> signUp (@RequestBody @Valid MemberRequestDto memberRequestDto){
        return memberService.signUp(memberRequestDto);
    }

    @PostMapping("/api/member/login")
    public ResponseEntity <GlobalResponseDto> login (@RequestBody @Valid MemberRequestDto memberRequestDto, HttpServletResponse response){
        return memberService.login(memberRequestDto,response);
    }

    @PatchMapping("/api/member/password")
    public ResponseEntity <GlobalResponseDto> changePassword (@RequestBody @Valid MemberRequestDto memberRequestDto) {
        return memberService.changePassword(memberRequestDto);
    }
}
