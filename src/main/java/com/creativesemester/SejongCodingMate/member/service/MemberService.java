package com.creativesemester.SejongCodingMate.member.service;


import com.creativesemester.SejongCodingMate.member.dto.request.SignUpRequest;
import com.creativesemester.SejongCodingMate.member.entity.Member;
import com.creativesemester.SejongCodingMate.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String signUp(SignUpRequest signUpRequest) {
        memberRepository.save(Member.of(signUpRequest.getStudentId(),signUpRequest.getPassword()));
        return "회원가입 성공했습니다.";
    }

    public ResponseEntity <Object> login(SignUpRequest signUpRequest) {
        Optional <Member> member = memberRepository.findByStudentId(signUpRequest.getStudentId());

        if(member.isEmpty()){
            return ResponseEntity.badRequest()
                    .body("회원이 없습니다.");
        }

        if(!member.get().getPassword().equals(signUpRequest.getPassword())){
            return ResponseEntity.badRequest()
                    .body("비밀번호가 틀렸습니다.");
        }

        return ResponseEntity.ok()
                .body("로그인 성공");
    }
}
