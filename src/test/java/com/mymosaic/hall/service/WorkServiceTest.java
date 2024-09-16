package com.mymosaic.hall.service;

import com.mymosaic.common.file.FileManger;
import com.mymosaic.hall.common.WorkTypeConverter;
import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.constant.WorkSubcategoryConst;
import com.mymosaic.hall.dto.VideoWorkDto;
import com.mymosaic.hall.dto.WorkDto;
import com.mymosaic.hall.repository.MemoryWorkRepository;
import com.mymosaic.hall.web.WorkAddForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WorkServiceTest {

    MemoryWorkRepository workRepository = new MemoryWorkRepository();
    FileManger fileManger = new FileManger();
    WorkTypeConverter workTypeConverter = new WorkTypeConverter(fileManger);
    WorkService workService = new WorkService(workRepository, workTypeConverter, fileManger);

    @AfterEach
    void afterEach(){
        workRepository.clearStore();
    }

    @Test
    void saveWork() throws IOException {
        //given
        WorkAddForm workAddForm = new WorkAddForm();
        workAddForm.setName("테스트 영상1");
        workAddForm.setContent("테스트 내용1");
        workAddForm.setCategoryId(WorkCategoryConst.VIDEO);
        workAddForm.setSubCategoryId(WorkSubcategoryConst.MOVIE);

        WorkAddForm workAddForm2 = new WorkAddForm();
        workAddForm2.setName("테스트 영상2");
        workAddForm2.setContent("테스트 내용2");
        workAddForm2.setCategoryId(WorkCategoryConst.VIDEO);
        workAddForm2.setSubCategoryId(WorkSubcategoryConst.DRAMA);

        workService.saveWork(workAddForm, 1L);
        workService.saveWork(workAddForm2, 1L);

        //when
        List<WorkDto> worksByMemberId = workService.findWorksByMemberId(1L, null);
        List<VideoWorkDto> videoWorkDtoList = worksByMemberId.stream().map(w -> (VideoWorkDto)w).toList();

        //then
        assertThat(worksByMemberId.size()).isEqualTo(2);
        assertThat(videoWorkDtoList.getFirst().getSubCategoryId()).isEqualTo(WorkSubcategoryConst.MOVIE);
    }
}