package com.mymosaic.diary.dto;

import com.mymosaic.common.file.UploadFile;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class DiaryEditParam {

    private boolean isPublic;
    private String title;
    private String content;
    private List<UploadFile> files = new ArrayList<>();
    private LocalDate localDate;
}
