package com.creativesemester.SejongCodingMate.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessType {

    /*============================ SUCCESS ================================*/

    //User 관련
    SIGN_UP_SUCCESS(200, "회원 가입에 성공했습니다."),
    LOG_IN_SUCCESS(200, "로그인되었습니다."),
    CHANGE_PASSWORD(200, "비밀번호가 변경되었습니다."),
    USER_EXIST(200, "존재하는 회원입니다."),
    SEND_TEMPORARY_PASSWORD(200, "임시 비밀번호를 발급했습니다. 메일을 확인해주세요."),

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

    CODE_CREATE_SUCCESS(200, "코드추가 성공했습니다."),
    GET_CODE_SUCCESS(200, "코드조회 성공했습니다.");



    private final int statusCode;
    private final String message;
}