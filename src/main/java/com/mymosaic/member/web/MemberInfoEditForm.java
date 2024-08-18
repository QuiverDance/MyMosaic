package com.mymosaic.member.web;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MemberInfoEditForm {

    @Length(max = 10)
    private String introduction = "";
    private MultipartFile profileImg;
}
