package com.creativesemester.SejongCodingMate.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    /*============================ SUCCESS ================================*/

    //User 관련
    SIGN_UP_SUCCESS(200, "회원 가입에 성공했습니다. 비밀번호를 변경해주세요"),
    LOG_IN_SUCCESS(200, "로그인되었습니다."),
    SEJONG_AUTHENTICATION(200, "학생 인증이 완료되었습니다."),
    CHANGE_PASSWORD(200, "비밀번호가 변경되었습니다."),

    // Test 관련

    POST_TEST_SUCCESS(200, "POST 테스트 성공했습니다."),

    GET_TEST_SUCCESS(200, "GET 테스트 성공했습니다."),
    /*============================ FAIL ================================*/

    //Global
    NOT_VALID_REQUEST(400,  "유효하지 않은 요청입니다."),
    NOT_VALID_TOKEN(400,"유효한 토큰이 아닙니다."),
    TOKEN_NOT_FOUND(400,  "토큰이 없습니다."),

    //User 관련
    USER_EXIST(400, "이미 존재하는 회원입니다."),
    USER_NICKNAME_EXIST(400, "이미 존재하는 닉네임입니다."),
    USER_ACCOUNT_NOT_EXIST(400,  "계정 정보가 존재하지 않습니다."),
    USER_NOT_FOUND(400, "사용자가 존재하지 않습니다."),
    PASSWORD_MISMATCH(400,  "비밀번호가 일치하지 않습니다."),
    SEJONG_AUTHENTICATION_FAILED(400, "학생 인증에 실패하였습니다.");

    private final int statusCode;
    private final String message;
}