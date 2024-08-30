package com.mymosaic.member.web;

import com.mymosaic.common.file.UploadFile;
import com.mymosaic.member.dto.MemberEditParam;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MemberInfoEditForm {

    @Length(max = 10)
    private String introduction = "";
    private MultipartFile profileImg;

    public MemberEditParam toParam(UploadFile uploadFile){
        return new MemberEditParam(introduction, uploadFile);
    }
}
