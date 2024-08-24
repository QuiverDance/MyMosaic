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
    private Boolean bookmark;
    private String title;
    private String content;
    private LocalDate diaryDate;
    private LocalDateTime uploadTime;
    private LocalDateTime lastTime;

    private List<UploadFile> files;
    private List<String> encodedFiles;

    public DiaryDto(Diary diary){
        id = diary.getId();
        memberId = diary.getMemberId();
        isPublic = diary.getIsPublic();
        bookmark = diary.getBookmark();
        title = diary.getTitle();
        content = diary.getContent();
        diaryDate = diary.getDiaryDate();
        uploadTime = diary.getUploadTime();
        lastTime = diary.getLastTime();
        files = diary.getFiles();
    }
}
