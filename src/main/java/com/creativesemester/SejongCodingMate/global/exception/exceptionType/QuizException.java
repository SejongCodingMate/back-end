package com.creativesemester.SejongCodingMate.global.exception.exceptionType;


import com.creativesemester.SejongCodingMate.global.response.ErrorType;

public class QuizException extends GlobalException{

    public QuizException(ErrorType statusCode) {
        super(statusCode);
    }
}
