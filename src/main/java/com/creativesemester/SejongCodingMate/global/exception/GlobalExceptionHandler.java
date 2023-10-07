package com.creativesemester.SejongCodingMate.global.exception;

import com.creativesemester.SejongCodingMate.global.exception.exceptionType.*;
import com.creativesemester.SejongCodingMate.global.response.ErrorType;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // MemberException Handler
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<GlobalResponseDto> handleUserException(MemberException e) {
        ErrorType errorType = e.getStatusCode();
        log.error(errorType.getMessage());
        return ResponseEntity.badRequest()
                .body(GlobalResponseDto.of(errorType));
    }

    // ChapterException
    @ExceptionHandler(ChapterException.class)
    public ResponseEntity<GlobalResponseDto> handleCompanyException(ChapterException e) {
        ErrorType errorType = e.getStatusCode();
        log.error(errorType.getMessage());
        return ResponseEntity.badRequest()
                .body(GlobalResponseDto.of(errorType));
    }

    // DialogueException
    @ExceptionHandler(DialogueException.class)
    public ResponseEntity<GlobalResponseDto> handleVisitFormException(DialogueException e) {
        ErrorType errorType = e.getStatusCode();
        log.error(errorType.getMessage());
        return ResponseEntity.badRequest()
                .body(GlobalResponseDto.of(errorType));
    }

    // QuizException
    @ExceptionHandler(QuizException.class)
    public ResponseEntity<GlobalResponseDto> handleAccessRecordException(QuizException e) {
        ErrorType errorType = e.getStatusCode();
        log.error(errorType.getMessage());
        return ResponseEntity.badRequest()
                .body(GlobalResponseDto.of(errorType));
    }

    // StoryException
    @ExceptionHandler(StoryException.class)
    public ResponseEntity<GlobalResponseDto> handleAccessException(StoryException e) {
        ErrorType errorType = e.getStatusCode();
        log.error(errorType.getMessage());
        return ResponseEntity.badRequest()
                .body(GlobalResponseDto.of(errorType));
    }

    // CodeException
    @ExceptionHandler(CodeException.class)
    public ResponseEntity<GlobalResponseDto> handleAccessException(CodeException e) {
        ErrorType errorType = e.getStatusCode();
        log.error(errorType.getMessage());
        return ResponseEntity.badRequest()
                .body(GlobalResponseDto.of(errorType));
    }

    // GlobalException Handler
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<GlobalResponseDto> handleGlobalException(GlobalException e) {
        ErrorType errorType = e.getStatusCode();
        log.error(errorType.getMessage());
        return ResponseEntity.badRequest()
                .body(GlobalResponseDto.of(errorType));
    }

    // Validation Handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponseDto> handleMethodException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error(message);
        return ResponseEntity.badRequest()
                .body(new GlobalResponseDto(HttpStatus.BAD_REQUEST.value(), message, null));
    }

}