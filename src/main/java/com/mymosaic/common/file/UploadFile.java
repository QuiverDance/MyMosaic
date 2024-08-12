package com.mymosaic.common.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class UploadFile {

    private String uploadFileName;
    private String storeFileName;
}
