package com.creativesemester.SejongCodingMate.domain.member.controller;


import com.creativesemester.SejongCodingMate.domain.member.dto.request.AuthRequestDto;
import com.creativesemester.SejongCodingMate.domain.member.dto.response.AuthResponseDto;
import com.creativesemester.SejongCodingMate.domain.member.service.AuthService;
import com.creativesemester.SejongCodingMate.domain.member.service.MemberService;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.ResponseCode;
import com.google.gson.JsonObject;
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
    private final AuthService authService;

    @PostMapping("/api/member/sign-up")
    public ResponseEntity <GlobalResponseDto> signUp (@RequestBody @Valid AuthRequestDto authRequestDto){
        return memberService.signUp(authRequestDto);
    }

    @PostMapping("/api/member/login")
    public ResponseEntity <GlobalResponseDto> login (@RequestBody @Valid AuthRequestDto authRequestDto, HttpServletResponse response){
        return memberService.login(authRequestDto,response);
    }

    @PostMapping("/api/member/sejong-login")
    public ResponseEntity <GlobalResponseDto> authenticateSejongStudent (@RequestBody @Valid AuthRequestDto authRequestDto) {
        JsonObject body = authService.authenticateSejongStudent(authRequestDto);
        if (body == null) {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.SEJONG_AUTHENTICATION_FAILED));
        } else {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.SEJONG_AUTHENTICATION,
                    new AuthResponseDto(authRequestDto.getId())));
        }
    }

    @PatchMapping("/api/member/password")
    public ResponseEntity <GlobalResponseDto> changePassword (@RequestBody @Valid AuthRequestDto authRequestDto) {
        return memberService.changePassword(authRequestDto);
    }
}
