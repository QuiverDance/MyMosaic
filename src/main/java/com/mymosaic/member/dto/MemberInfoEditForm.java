package com.mymosaic.member.dto;

import com.mymosaic.common.constant.FileNameConst;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MemberInfoEditForm {

    @NotNull
    private Long id;

    @NotNull
    private String loginId;

    @NotNull
    private String name;

    private String profileImgName = FileNameConst.PROFILE_IMG_DEFAULT;
    private String introduction = "";
    private MultipartFile profileImg;
}
