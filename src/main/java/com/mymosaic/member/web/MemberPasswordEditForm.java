package com.mymosaic.member.web;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MemberPasswordEditForm {

    @NotEmpty
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,15}$", message = "숫자, 문자 또는 특수문자가 하나 이상 있어야 합니다.")
    private String password;

    @NotEmpty
    private String password2;
}
