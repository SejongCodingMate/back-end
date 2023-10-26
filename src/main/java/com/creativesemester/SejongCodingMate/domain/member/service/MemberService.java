package com.creativesemester.SejongCodingMate.domain.member.service;


import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
import com.creativesemester.SejongCodingMate.domain.chapter.repository.ChapterRepository;
import com.creativesemester.SejongCodingMate.domain.member.dto.request.MemberIdRequestDto;
import com.creativesemester.SejongCodingMate.domain.member.dto.request.MemberRequestDto;
import com.creativesemester.SejongCodingMate.domain.member.dto.response.MemberResponseDto;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.domain.member.repository.MemberRepository;
import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
import com.creativesemester.SejongCodingMate.domain.story.repository.StoryRepository;
import com.creativesemester.SejongCodingMate.global.jwt.JwtUtil;
import com.creativesemester.SejongCodingMate.global.jwt.TokenDto;
import com.creativesemester.SejongCodingMate.global.response.ErrorType;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.SuccessType;
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
    private final ChapterRepository chapterRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    // 1. 회원 가입
    @Transactional
    public ResponseEntity<GlobalResponseDto> signUp(MemberRequestDto memberRequestDto) {

        Optional<Member> member = memberRepository.findByMemberId(memberRequestDto.getMemberId());

        if (member.isPresent()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ErrorType.USER_EXIST));
        }

        Optional<Chapter> chapter = chapterRepository.findById(1L);

        if (chapter.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ErrorType.CHAPTER_NOT_FOUND));
        }

        Optional<Story> story = storyRepository.findById(1L);

        if (story.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ErrorType.STORY_NOT_FOUND));
        }

        String encodedPassword = passwordEncoder.encode(memberRequestDto.getPassword());
        memberRepository.save(Member.of(memberRequestDto.getMemberId(), encodedPassword, story.get(), chapter.get(), false, "User"));
        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.SIGN_UP_SUCCESS));
    }

    public ResponseEntity<GlobalResponseDto> login(MemberRequestDto memberRequestDto, HttpServletResponse response) {

        Optional<Member> member = memberRepository.findByMemberId(memberRequestDto.getMemberId());

        if (member.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ErrorType.USER_NOT_FOUND));
        }

        if (!passwordEncoder.matches(memberRequestDto.getPassword(), member.get().getPassword())) {
            return ResponseEntity.ok(GlobalResponseDto.of(ErrorType.PASSWORD_MISMATCH));
        }

        TokenDto tokenDto = new TokenDto(jwtUtil.createToken(memberRequestDto.getMemberId()));
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, tokenDto.getAccessToken());

        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.LOG_IN_SUCCESS,
                MemberResponseDto.of(member.get().getStory().getId(), member.get().getChapter().getId(),
                        member.get().getHasTemporaryPassword(), member.get().getName())));
    }

    @Transactional
    public ResponseEntity<GlobalResponseDto> changePassword(MemberRequestDto memberRequestDto) {

        Optional<Member> member = memberRepository.findByMemberId(memberRequestDto.getMemberId());

        if (member.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(GlobalResponseDto.of(ErrorType.USER_NOT_FOUND));
        }

        String encodedPassword = passwordEncoder.encode(memberRequestDto.getPassword());
        member.get().changePassword(encodedPassword);
        member.get().changeHasTemporaryPassword(false);
        memberRepository.save(member.get());

        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.CHANGE_PASSWORD));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<GlobalResponseDto> isExistMember(String memberId) {

        Optional<Member> member = memberRepository.findByMemberId(memberId);

        if (member.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(GlobalResponseDto.of(ErrorType.USER_NOT_FOUND, false));
        }

        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.USER_EXIST, true));
    }

    @Transactional()
    public ResponseEntity<GlobalResponseDto> sendPasswordEmail(MemberIdRequestDto memberIdRequestDto) {

        Optional<Member> member = memberRepository.findByMemberId(memberIdRequestDto.getMemberId());

        if (member.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(GlobalResponseDto.of(ErrorType.USER_NOT_FOUND, false));
        }

        String temporaryPassword = mailService.createMail(member.get().getMemberId());

        String encodedPassword = passwordEncoder.encode(temporaryPassword);
        member.get().changePassword(encodedPassword);
        member.get().changeHasTemporaryPassword(Boolean.TRUE);
        memberRepository.save(member.get());

        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.SEND_TEMPORARY_PASSWORD));

    }
}
