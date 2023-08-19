package com.creativesemester.SejongCodingMate.domain.test;


import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.domain.member.repository.MemberRepository;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {


    @Transactional
    public ResponseEntity<GlobalResponseDto> postTest(TestRequestDto testRequestDto, Member member) {

        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.POST_TEST_SUCCESS,testRequestDto.getTest()));
    }

    public ResponseEntity<GlobalResponseDto> getTest(Member member) {
        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.GET_TEST_SUCCESS));

    }
}
