package com.creativesemester.SejongCodingMate.global.exception.exceptionType;


import com.creativesemester.SejongCodingMate.global.response.ErrorType;

public class StoryException extends GlobalException{

    public StoryException(ErrorType statusCode) {
        super(statusCode);
    }
}
