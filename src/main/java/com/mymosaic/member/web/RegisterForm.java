package com.mymosaic.member.web;

import com.mymosaic.member.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterForm {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String loginId;

    @NotEmpty
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,15}$")
    private String password;

    @NotEmpty
    private String password2;

    @NotEmpty
    @Size(min = 2, max = 10)
    private String name;

    public Member toMember(){
        return new Member(loginId, name, password);
    }
}
