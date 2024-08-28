package com.mymosaic.diary.web;

import com.mymosaic.common.file.UploadFile;
import com.mymosaic.diary.domain.Diary;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class DiaryAddForm {

    private Boolean isPublic;
    private String title;
    private String content;
    private LocalDate diaryDate = LocalDate.now();

    private List<MultipartFile> files = new ArrayList<>();

    public Diary toDiary(Long memberId, List<UploadFile> files){
        return new Diary(memberId, isPublic, title, content, files, diaryDate);
    }
}
