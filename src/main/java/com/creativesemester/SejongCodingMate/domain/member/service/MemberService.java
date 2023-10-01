package com.creativesemester.SejongCodingMate.domain.member.service;


import com.creativesemester.SejongCodingMate.domain.member.dto.request.MemberRequestDto;
import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
import com.creativesemester.SejongCodingMate.domain.story.repository.StoryRepository;
import com.creativesemester.SejongCodingMate.global.jwt.JwtUtil;
import com.creativesemester.SejongCodingMate.global.jwt.TokenDto;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.domain.member.repository.MemberRepository;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.ResponseCode;
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
    private final StoryRepository storyRepository;
    private final PasswordEncoder passwordEncoder;

    // 1. 회원 가입
    @Transactional
    public ResponseEntity<GlobalResponseDto> signUp(MemberRequestDto memberRequestDto) {

        Optional<Member> member = memberRepository.findByMemberId(memberRequestDto.getMemberId());

        if (member.isPresent()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.USER_EXIST));
        }

        Optional<Story> story = storyRepository.findById(1L);

        if (story.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.STORY_NOT_FOUND));
        }

        String encodedPassword = passwordEncoder.encode(memberRequestDto.getPassword());
        memberRepository.save(Member.of(memberRequestDto.getMemberId(), encodedPassword, story.get()));
        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.SIGN_UP_SUCCESS));
    }

    public ResponseEntity<GlobalResponseDto> login(MemberRequestDto memberRequestDto, HttpServletResponse response) {

        Optional<Member> member = memberRepository.findByMemberId(memberRequestDto.getMemberId());

        if (member.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.USER_NOT_FOUND));
        }

        if (!passwordEncoder.matches(memberRequestDto.getPassword(), member.get().getPassword())) {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.PASSWORD_MISMATCH));
        }

        TokenDto tokenDto = new TokenDto(jwtUtil.createToken(memberRequestDto.getMemberId()));
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, tokenDto.getAccessToken());

        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.LOG_IN_SUCCESS));
    }

    @Transactional
    public ResponseEntity<GlobalResponseDto> changePassword(MemberRequestDto memberRequestDto) {

        Optional<Member> member = memberRepository.findByMemberId(memberRequestDto.getMemberId());

        if (member.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.USER_NOT_FOUND));
        }

        String encodedPassword = passwordEncoder.encode(memberRequestDto.getPassword());
        member.get().changePassword(encodedPassword);
        memberRepository.save(member.get());

        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.CHANGE_PASSWORD));
    }
}
