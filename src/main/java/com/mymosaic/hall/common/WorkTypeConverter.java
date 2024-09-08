package com.mymosaic.hall.common;

import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.domain.VideoWork;
import com.mymosaic.hall.domain.Work;
import com.mymosaic.hall.dto.VideoWorkDto;
import com.mymosaic.hall.dto.WorkDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkTypeConverter {

    public static WorkDto convertToDto(Work work){
        WorkDto workDto = null;

        // categoryId에 따라 적절한 서브 타입으로 변환
        if(work.getCategoryId().equals(WorkCategoryConst.VIDEO)){
            VideoWork videoWork = (VideoWork)work;
            log.info("ids : {}", videoWork.getSubCategoryId());
            workDto = VideoWorkDto.builder()
                    .subCategoryId(videoWork.getSubCategoryId())
                    .genreIds(videoWork.getGenreIds())
                    .performers(videoWork.getPerformers())
                    .production(videoWork.getProduction())
                    .workImages(videoWork.getWorkImages())
                    .year(videoWork.getYear())
                    .build();
        }
        else if(work.getCategoryId().equals(WorkCategoryConst.TEXT)) {

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
        workDto.setRating(workDto.getRating());
        workDto.setVisibility(work.getVisibility());
        workDto.setCategoryId(work.getCategoryId());
        workDto.setCreatedTime(work.getCreatedTime());
        workDto.setLastTime(work.getLastTime());
        return workDto;
    }
}
