package com.mymosaic.diary.domain;

import com.mymosaic.common.file.UploadFile;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Diary {

    @Setter
    private Long id;

    private Long memberId;
    private Boolean isPublic;
    private Boolean bookmark = false;
    private String title;
    private String content;
    private List<UploadFile> files = new ArrayList<>();
    private LocalDate diaryDate;
    private final LocalDateTime uploadTime = LocalDateTime.now();
    private LocalDateTime lastTime = LocalDateTime.now();

    public Diary(Long memberId, Boolean isPublic, String title, String content, LocalDate diaryDate){
        this.memberId = memberId;
        this.title = title;
        this.isPublic = isPublic;
        this.content = content;
        this.diaryDate = diaryDate;
    }

    public void updateDiaryInfo(Boolean isPublic, String title, String content, List<UploadFile> files, LocalDate dairyDate){
        this.title = title;
        this.content = content;
        this.isPublic = isPublic;
        this.files = files;
        this.diaryDate = dairyDate;
        lastTime = LocalDateTime.now();
    }

    public void updateBookmark(boolean bookmark){
        this.bookmark = bookmark;
    }
}
