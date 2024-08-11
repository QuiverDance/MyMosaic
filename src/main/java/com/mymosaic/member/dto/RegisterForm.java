package com.mymosaic.member.dto;

import com.mymosaic.member.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterForm {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "이메일 형식이어야 합니다.")
    private String loginId;

    @NotEmpty
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,15}$", message = "숫자, 문자 또는 특수문자가 하나 이상 있어야 합니다.")
    private String password;

    @NotEmpty
    @Size(min = 2, max = 10)
    private String name;

    public Member toMember(){
        return new Member(loginId, name, password);
    }
}
