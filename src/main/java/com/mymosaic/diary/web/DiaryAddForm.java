package com.mymosaic.diary.web;

import com.mymosaic.common.file.UploadFile;
import com.mymosaic.diary.domain.Diary;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class DiaryAddForm {

    private Boolean isPublic;

    @NotEmpty
    @Length(max = 20)
    private String title;

    @NotEmpty
    @Length(max = 300)
    private String content;

    private LocalDate diaryDate = LocalDate.now();

    private List<MultipartFile> files = new ArrayList<>();

    public Diary toDiary(Long memberId, List<UploadFile> files){
        return new Diary(memberId, isPublic, title, content, files, diaryDate);
    }
}
