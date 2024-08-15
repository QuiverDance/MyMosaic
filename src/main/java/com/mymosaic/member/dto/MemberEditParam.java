package com.mymosaic.member.dto;

import com.mymosaic.common.file.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberEditParam {

    private String introduction;
    private UploadFile uploadFile;
}
