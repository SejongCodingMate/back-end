package com.creativesemester.SejongCodingMate.domain.test;


import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.ErrorType;
import com.creativesemester.SejongCodingMate.global.response.SuccessType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestService {


    @Transactional
    public ResponseEntity<GlobalResponseDto> postTest(TestRequestDto testRequestDto, Member member) {

        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.POST_TEST_SUCCESS,testRequestDto.getTest()));
    }

    public ResponseEntity<GlobalResponseDto> getTest(Member member) {
        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.GET_TEST_SUCCESS));

    }
}
