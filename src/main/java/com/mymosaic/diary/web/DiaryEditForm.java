package com.mymosaic.diary.web;

import com.mymosaic.common.file.UploadFile;
import com.mymosaic.diary.dto.DiaryEditParam;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class DiaryEditForm {

    private Boolean isPublic;

    @NotEmpty
    @Length(max = 20)
    private String title;

    @NotEmpty
    @Length(max = 300)
    private String content;

    private LocalDate diaryDate;

    private List<MultipartFile> files = new ArrayList<>();

    public DiaryEditParam toEditParam(List<UploadFile> uploadFiles){
        return new DiaryEditParam(
                isPublic, title, content, uploadFiles, diaryDate
        );
    }
}
