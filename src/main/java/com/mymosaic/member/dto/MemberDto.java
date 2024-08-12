package com.mymosaic.member.dto;

import com.mymosaic.member.domain.Member;
import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    private String loginId;
    private String name;
    private String profileUrl;
    private String introduction;
    private String profileImg;

    public MemberDto(Member member){
        id = member.getId();
        loginId = member.getLoginId();
        name = member.getName();
        profileUrl = member.getProfileUrl();
        introduction = member.getIntroduction();
    }
}
