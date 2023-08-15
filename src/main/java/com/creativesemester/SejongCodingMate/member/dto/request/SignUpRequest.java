package com.creativesemester.SejongCodingMate.member.dto.request;

import lombok.Getter;
import javax.validation.constraints.NotBlank;


@Getter
public class SignUpRequest {

    @NotBlank(message = "학번 형식이 올바르지 않습니다.")
    private String studentId;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    private String password;


}
