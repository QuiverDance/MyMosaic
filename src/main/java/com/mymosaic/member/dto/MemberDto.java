package com.mymosaic.member.dto;

import com.mymosaic.common.file.UploadFile;
import com.mymosaic.member.domain.Member;
import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    private String loginId;
    private String name;
    private String introduction;

    private UploadFile profile;
    private String profileImg; //encoded

    public MemberDto(Member member){
        id = member.getId();
        loginId = member.getLoginId();
        name = member.getName();
        profile = member.getProfile();
        introduction = member.getIntroduction();
    }
}
