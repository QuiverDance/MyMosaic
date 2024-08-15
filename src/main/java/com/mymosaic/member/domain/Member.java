package com.mymosaic.member.domain;

import com.mymosaic.common.constant.FileDirConst;
import com.mymosaic.common.constant.FileNameConst;
import com.mymosaic.common.file.DefaultFileManger;
import com.mymosaic.common.file.UploadFile;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Member {

    @Setter
    private Long id;

    private String loginId;
    private String name;
    private String password;
    private String introduction = "";
    private UploadFile profile = DefaultFileManger.createDefaultProfileFile();

    public Member(String loginId, String name, String password){
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }

    public void updateProfile(UploadFile profile){
        this.profile = profile;
    }

    public void updateIntroduction(String introduction){
        this.introduction = introduction;
    }

    public void updatePassword(String password){
        this.password = password;
    }
}
