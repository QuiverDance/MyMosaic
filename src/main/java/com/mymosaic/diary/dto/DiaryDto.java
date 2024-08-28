package com.mymosaic.diary.dto;

import com.mymosaic.common.file.UploadFile;
import com.mymosaic.diary.domain.Diary;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DiaryDto {

    private Long id;
    private Long memberId;
    private Boolean isPublic;
    private String title;
    private String content;
    private LocalDate diaryDate;
    private LocalDateTime createdTime;
    private LocalDateTime lastTime;

    private List<UploadFile> files;
    private List<String> encodedFiles;

    public DiaryDto(Diary diary){
        id = diary.getId();
        memberId = diary.getMemberId();
        isPublic = diary.getIsPublic();
        title = diary.getTitle();
        content = diary.getContent();
        diaryDate = diary.getDiaryDate();
        createdTime = diary.getCreatedTime();
        lastTime = diary.getLastTime();
        files = diary.getFiles();
    }
}
