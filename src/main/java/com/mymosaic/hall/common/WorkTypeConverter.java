package com.mymosaic.hall.common;

import com.mymosaic.common.constant.FileDirConst;
import com.mymosaic.common.file.FileManger;
import com.mymosaic.common.file.UploadFile;
import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.domain.TextWork;
import com.mymosaic.hall.domain.VideoWork;
import com.mymosaic.hall.domain.Work;
import com.mymosaic.hall.dto.TextWorkDto;
import com.mymosaic.hall.dto.VideoWorkDto;
import com.mymosaic.hall.dto.WorkDto;
import com.mymosaic.hall.web.WorkAddForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WorkTypeConverter {

    private final FileManger fileManger;
    public WorkDto convertToDto(Work work) throws IOException {
        WorkDto workDto = null;

        // categoryId에 따라 적절한 서브 타입으로 변환
        if(work.getCategoryId().equals(WorkCategoryConst.VIDEO)){
            VideoWork videoWork = (VideoWork)work;
            List<String> loadedImages = fileManger.loadImages(videoWork.getWorkImages());
            workDto = VideoWorkDto.builder()
                    .subCategoryId(videoWork.getSubCategoryId())
                    .genreIds(videoWork.getGenreIds())
                    .performers(videoWork.getPerformers())
                    .production(videoWork.getProduction())
                    .workImages(videoWork.getWorkImages())
                    .year(videoWork.getYear())
                    .loadedImages(loadedImages)
                    .build();
        }
        else if(work.getCategoryId().equals(WorkCategoryConst.TEXT)) {
            TextWork textWork = (TextWork)work;
            List<String> loadedImages = fileManger.loadImages(textWork.getWorkImages());
            workDto = TextWorkDto.builder()
                    .subCategoryId(textWork.getSubCategoryId())
                    .genreIds(textWork.getGenreIds())
                    .authors(textWork.getAuthors())
                    .publisher(textWork.getPublisher())
                    .workImages(textWork.getWorkImages())
                    .year(textWork.getYear())
                    .loadedImages(loadedImages)
                    .build();
        }
        else if(work.getCategoryId().equals(WorkCategoryConst.CHARACTER)){

        }
        else {
            throw new IllegalArgumentException("Unknown category id: " + work.getCategoryId());
        }

        // 공통 필드 설정
        workDto.setId(work.getId());
        workDto.setMemberId(work.getMemberId());
        workDto.setName(work.getName());
        workDto.setContent(work.getContent());
        workDto.setRating(work.getRating());
        workDto.setVisibility(work.getVisibility());
        workDto.setCategoryId(work.getCategoryId());
        workDto.setCreatedTime(work.getCreatedTime());
        workDto.setLastTime(work.getLastTime());
        return workDto;
    }

    public Work convertToWork(WorkAddForm form, Long memberId) throws IOException {
        Work work = null;
        if(form.getCategoryId().equals(WorkCategoryConst.VIDEO)){
            List<UploadFile> uploadFiles = fileManger.storeFiles(FileDirConst.WORK_DIR, form.getWorkImageFiles());
            work = VideoWork.builder()
                    .memberId(memberId)
                    .categoryId(form.getCategoryId())
                    .visibility(form.getVisibility())
                    .name(form.getName())
                    .content(form.getContent())
                    .rating(form.getRating())
                    .subCategoryId(form.getSubCategoryId())
                    .genreIds(form.getGenreIds())
                    .production(form.getProduction())
                    .performers(form.getPerformers())
                    .year(form.getYear())
                    .workImages(uploadFiles)
                    .build();
        }
        else if(form.getCategoryId().equals(WorkCategoryConst.TEXT)){
            List<UploadFile> uploadFiles = fileManger.storeFiles(FileDirConst.WORK_DIR, form.getWorkImageFiles());
            work = TextWork.builder()
                    .memberId(memberId)
                    .categoryId(form.getCategoryId())
                    .visibility(form.getVisibility())
                    .name(form.getName())
                    .content(form.getContent())
                    .rating(form.getRating())
                    .subCategoryId(form.getSubCategoryId())
                    .genreIds(form.getGenreIds())
                    .publisher(form.getPublisher())
                    .authors(form.getAuthors())
                    .year(form.getYear())
                    .workImages(uploadFiles)
                    .build();
        }
        else if(form.getCategoryId().equals(WorkCategoryConst.CHARACTER)){

        }
        else{
            throw new IllegalArgumentException("Invalid categoryId");
        }
        return work;
    }
}
