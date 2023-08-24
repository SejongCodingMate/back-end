package com.creativesemester.SejongCodingMate.domain.member.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AuthRequestDto {
    @NotBlank(message = "학번을 입력해주세요.")
    private String id;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String pw;

}
