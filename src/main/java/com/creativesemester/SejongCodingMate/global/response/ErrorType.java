package com.creativesemester.SejongCodingMate.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

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

    QUIZ_NOT_FOUND(400, "퀴즈를 찾을 수 없습니다."),

    CODE_NOT_FOUND(400, "코드를 찾을 수 없습니다."),

    CHAPTER_NOT_FOUND(400, "챕터를 찾을 수 없습니다.");


    private final int statusCode;
    private final String message;
}