package com.creativesemester.SejongCodingMate.domain.member.service;


import com.creativesemester.SejongCodingMate.domain.member.dto.request.AuthRequestDto;
import com.creativesemester.SejongCodingMate.domain.member.dto.response.AuthResponseDto;
import com.creativesemester.SejongCodingMate.global.jwt.JwtUtil;
import com.creativesemester.SejongCodingMate.global.jwt.TokenDto;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.domain.member.repository.MemberRepository;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.ResponseCode;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<GlobalResponseDto> signUp(AuthRequestDto authRequestDto) {
        //1. 세종대 인증
        JsonObject body = authService.authenticateSejongStudent(authRequestDto);

        if (body == null) {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.SEJONG_AUTHENTICATION_FAILED));
        }

        //2. 이미 존재하는 회원일 경우 회원 가입 실패
        if (memberRepository.findByStudentId(authRequestDto.getId()).isPresent()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.USER_EXIST));
        }
        String major = body.get("major").getAsString();
        String grade = body.get("grade").getAsString();

        //3. 회원 정보 저장 - 비밀번호는 회원가입 후 즉시 변경
        memberRepository.save(Member.of(authRequestDto.getId(), authRequestDto.getId(), major, grade));

        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.SIGN_UP_SUCCESS,
                AuthResponseDto.of(authRequestDto.getId())));
    }

    public ResponseEntity<GlobalResponseDto> login(AuthRequestDto authRequestDto, HttpServletResponse response) {

        Optional<Member> member = memberRepository.findByStudentId(authRequestDto.getId());

        if (member.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.USER_NOT_FOUND));
        }

        if (!passwordEncoder.matches(authRequestDto.getPw(), member.get().getPassword())) {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.PASSWORD_MISMATCH));
        }

        TokenDto tokenDto = new TokenDto(jwtUtil.createToken(authRequestDto.getId()));
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, tokenDto.getAccessToken());


        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.LOG_IN_SUCCESS));
    }

    @Transactional
    public ResponseEntity<GlobalResponseDto> changePassword(AuthRequestDto authRequestDto) {
        Optional<Member> member = memberRepository.findByStudentId(authRequestDto.getId());
        if (member.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.USER_NOT_FOUND));
        }
        String encodedPassword = passwordEncoder.encode(authRequestDto.getPw());
        member.get().changePassword(encodedPassword);
        memberRepository.save(member.get());
        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.CHANGE_PASSWORD));
    }
}
