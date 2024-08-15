package com.mymosaic.common.file;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFile {

    private String uploadFileName;
    private String storeFileName;
    private String filePath;

    public String extractExt() {
        int pos = storeFileName.lastIndexOf(".");
        return storeFileName.substring(pos + 1);
    }
}
