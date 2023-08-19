package com.creativesemester.SejongCodingMate.domain.member.service;


import com.creativesemester.SejongCodingMate.global.jwt.JwtUtil;
import com.creativesemester.SejongCodingMate.global.jwt.TokenDto;
import com.creativesemester.SejongCodingMate.domain.member.dto.request.SignUpRequest;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    @Transactional
    public String signUp(SignUpRequest signUpRequest) {
        memberRepository.save(Member.of(signUpRequest.getStudentId(), signUpRequest.getPassword()));
        return "회원가입 성공했습니다.";
    }

    public ResponseEntity<Object> login(SignUpRequest signUpRequest, HttpServletResponse response) {

        Optional<Member> member = memberRepository.findByStudentId(signUpRequest.getStudentId());

        if (member.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("회원이 없습니다.");
        }

        if (!member.get().getPassword().equals(signUpRequest.getPassword())) {
            return ResponseEntity.badRequest()
                    .body("비밀번호가 틀렸습니다.");
        }

        TokenDto tokenDto = new TokenDto(jwtUtil.createToken(signUpRequest.getStudentId()));
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, tokenDto.getAccessToken());


        return ResponseEntity.ok()
                .body("로그인 성공");
    }
}
