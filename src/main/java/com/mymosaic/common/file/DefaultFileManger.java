package com.mymosaic.common.file;

import com.mymosaic.common.constant.FileDirConst;
import com.mymosaic.common.constant.FileNameConst;

public class DefaultFileManger {

    public static UploadFile createDefaultProfileFile(){
        return new UploadFile(FileNameConst.PROFILE_IMG_DEFAULT, FileNameConst.PROFILE_IMG_DEFAULT,
                FileDirConst.MEMBER_PROFILE_DIR + FileNameConst.PROFILE_IMG_DEFAULT);
    }
}
