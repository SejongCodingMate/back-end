package com.creativesemester.SejongCodingMate.domain.member.controller;


import com.creativesemester.SejongCodingMate.domain.member.dto.request.SignUpRequest;
import com.creativesemester.SejongCodingMate.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity <Object> signUp (@RequestBody @Valid SignUpRequest signUpRequest){
        return ResponseEntity.ok(memberService.signUp(signUpRequest));
    }

    @PostMapping("/api/member/login")
    public ResponseEntity <Object> login (@RequestBody @Valid SignUpRequest signUpRequest, HttpServletResponse response){
        return memberService.login(signUpRequest,response);
    }




}
