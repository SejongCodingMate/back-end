package com.creativesemester.SejongCodingMate.global.exception.exceptionType;


import com.creativesemester.SejongCodingMate.global.response.ErrorType;

public class ChapterException extends GlobalException{

    public ChapterException(ErrorType statusCode) {
        super(statusCode);
    }
}
