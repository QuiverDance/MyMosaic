package com.mymosaic.diary.web;

import com.mymosaic.diary.dto.DiaryEditParam;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class DiaryEditForm {

    private Boolean isPublic;
    private String title;
    private String content;
    private LocalDate diaryDate;

    private List<MultipartFile> files = new ArrayList<>();

    public DiaryEditParam toEditParam(){
        return new DiaryEditParam(
                isPublic, title, content, new ArrayList<>(), diaryDate
        );
    }
}
