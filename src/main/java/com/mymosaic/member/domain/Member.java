package com.mymosaic.member.domain;

import com.mymosaic.common.constant.FileNameConst;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Member {

    @Setter
    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    private String profileImgName = FileNameConst.PROFILE_IMG_DEFAULT;
    private String introduction = "";

    public Member(String loginId, String name, String password){
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }

    public void updateProfileImgUrl(String profileImgUrl){
        this.profileImgName = profileImgUrl;
    }

    public void updateIntroduction(String introduction){
        this.introduction = introduction;
    }
}
