package com.mymosaic.member.web;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MemberInfoEditForm {

    private String introduction = "";
    private MultipartFile profileImg;
}
