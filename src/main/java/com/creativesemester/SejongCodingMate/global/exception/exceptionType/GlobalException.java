package com.creativesemester.SejongCodingMate.global.exception.exceptionType;

import com.creativesemester.SejongCodingMate.global.response.ErrorType;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class GlobalException extends RuntimeException {

    private ErrorType statusCode;
    public GlobalException(ErrorType statusCode) {
        this.statusCode = statusCode;
    }
}