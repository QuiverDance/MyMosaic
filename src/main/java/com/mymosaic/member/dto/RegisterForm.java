package com.mymosaic.member.dto;

import com.mymosaic.member.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterForm {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;

    public Member toMember(){
        return new Member(loginId, name, password);
    }
}
