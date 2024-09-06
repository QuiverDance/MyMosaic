package com.mymosaic.hall.service;

import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.constant.WorkGenreConst;
import com.mymosaic.hall.constant.WorkSubcategoryConst;
import com.mymosaic.hall.dto.VideoWorkDto;
import com.mymosaic.hall.dto.WorkDto;
import com.mymosaic.hall.repository.MemoryWorkRepository;
import com.mymosaic.hall.web.VideoWorkAddForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

class WorkServiceTest {

    MemoryWorkRepository workRepository = new MemoryWorkRepository();
    WorkService workService = new WorkService(workRepository);

    @AfterEach
    void afterEach(){
        workRepository.clearStore();
    }

    @Test
    void saveWork(){
        //given
        VideoWorkAddForm workAddForm = new VideoWorkAddForm();
        workAddForm.setName("테스트 영상1");
        workAddForm.setContent("테스트 내용1");
        workAddForm.setCategoryId(WorkCategoryConst.VIDEO);
        workAddForm.setSubCategory(WorkSubcategoryConst.MOVIE);

        VideoWorkAddForm workAddForm2 = new VideoWorkAddForm();
        workAddForm2.setName("테스트 영상2");
        workAddForm2.setContent("테스트 내용2");
        workAddForm2.setCategoryId(WorkCategoryConst.VIDEO);
        workAddForm2.setSubCategory(WorkSubcategoryConst.DRAMA);

        workService.saveWork(workAddForm, 1L);
        workService.saveWork(workAddForm2, 1L);

        //when
        List<WorkDto> worksByMemberId = workService.findWorksByMemberId(1L);
        List<VideoWorkDto> videoWorkDtoList = worksByMemberId.stream().map(w -> (VideoWorkDto)w).toList();

        //then
        assertThat(worksByMemberId.size()).isEqualTo(2);
        assertThat(videoWorkDtoList.getFirst().getSubCategory()).isEqualTo(WorkSubcategoryConst.MOVIE);
    }
}