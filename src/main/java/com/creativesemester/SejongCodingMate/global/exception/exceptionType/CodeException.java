package com.creativesemester.SejongCodingMate.global.exception.exceptionType;

import com.creativesemester.SejongCodingMate.global.response.ErrorType;

public class CodeException extends GlobalException {
    public CodeException(ErrorType statusCode) {
        super(statusCode);
    }
}
