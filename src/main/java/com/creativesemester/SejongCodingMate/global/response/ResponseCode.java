package com.creativesemester.SejongCodingMate.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    /*============================ SUCCESS ================================*/

    //User 관련
    SIGN_UP_SUCCESS(200, "회원 가입에 성공했습니다."),
    LOG_IN_SUCCESS(200, "로그인되었습니다."),
    CHANGE_PASSWORD(200, "비밀번호가 변경되었습니다."),

    // Test 관련
    POST_TEST_SUCCESS(200, "POST 테스트 성공했습니다."),
    GET_TEST_SUCCESS(200, "GET 테스트 성공했습니다."),

    // Domain 관련
    COURSE_CREATE_SUCCESS(200, "과목추가 성공했습니다."),
    GET_COURSE_SUCCESS(200, "과목조회 성공했습니다."),

    CHAPTER_CREATE_SUCCESS(200, "단원추가 성공했습니다."),
    GET_CHAPTER_SUCCESS(200, "단원조회 성공했습니다."),

    CONTENTS_CREATE_SUCCESS(200, "개념추가 성공했습니다."),
    GET_CONTENTS_SUCCESS(200, "개념조회 성공했습니다."),

    PROBLEM_CREATE_SUCCESS(200, "문제추가 성공했습니다."),
    PROBLEM_GET_SUCCESS(200, "문제조회 성공했습니다."),

    CODE_ACCEPT(200, "정답입니다."),
    CODE_WRONG_ANSWER(200, "틀렸습니다."),
    CODE_EXECUTE_ERROR(200, "코드 실행 중 오류가 발생했습니다."),
    CODE_TIMEOUT(200, "시간초과입니다."),
    CODE_COMPILE_ERROR(200, "컴파일에 실패하였습니다."),

    QUIZ_CREATE_SUCCESS(200, "퀴즈추가 성공했습니다."),
    GET_QUIZ_SUCCESS(200, "퀴즈조회 성공했습니다."),


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

    //Domain 관련
    PROBLEM_NOT_FOUND(400, "문제를 찾을 수 없습니다."),

    CODE_EXCEPTION(400, "코드 실행에 실패했습니다."),

    STORY_NOT_FOUND(400, "스토리를 찾을 수 없습니다."),

    QUIZ_NOT_FOUND(400, "퀴즈를 찾을 수 없습니다.");


    private final int statusCode;
    private final String message;
}