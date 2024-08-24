package com.mymosaic.diary.dto;

import com.mymosaic.common.file.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaryEditParam {

    private Boolean isPublic;
    private String title;
    private String content;
    private List<UploadFile> files = new ArrayList<>();
    private LocalDate diaryDate;
}
