package com.creativesemester.SejongCodingMate.global.exception.exceptionType;


import com.creativesemester.SejongCodingMate.global.response.ErrorType;

public class DialogueException extends GlobalException{

    public DialogueException(ErrorType statusCode) {
        super(statusCode);
    }
}
