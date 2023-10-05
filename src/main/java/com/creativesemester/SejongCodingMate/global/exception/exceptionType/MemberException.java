package com.creativesemester.SejongCodingMate.global.exception.exceptionType;


import com.creativesemester.SejongCodingMate.global.response.ErrorType;

public class MemberException extends GlobalException {

    public MemberException(ErrorType statusCode) {
        super(statusCode);
    }
}