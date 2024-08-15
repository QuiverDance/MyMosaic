package com.mymosaic.member.dto;

import com.mymosaic.member.domain.Member;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MemberDto {

    private Long id;
    private String loginId;
    private String name;
    private String profileImgName;
    private String introduction;
    private String profileImg; //encoded

    public MemberDto(Member member){
        id = member.getId();
        loginId = member.getLoginId();
        name = member.getName();
        profileImgName = member.getProfileImgName();
        introduction = member.getIntroduction();
    }
}
