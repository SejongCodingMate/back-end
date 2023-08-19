package com.creativesemester.SejongCodingMate.member.controller;


import com.creativesemester.SejongCodingMate.member.dto.request.SignUpRequest;
import com.creativesemester.SejongCodingMate.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity <Object> login (@RequestBody @Valid SignUpRequest signUpRequest){
        return memberService.login(signUpRequest);
    }




}
