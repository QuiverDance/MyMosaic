package com.mymosaic.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberInfoEditForm {

    @NotNull
    private Long id;

    @NotNull
    private String loginId;

    @NotNull
    private String name;

    private String profileUrl;
    private String introduction;
}
