package com.mymosaic.hall.repository;

import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.constant.WorkGenreConst;
import com.mymosaic.hall.constant.WorkSubcategoryConst;
import com.mymosaic.hall.domain.VideoWork;
import com.mymosaic.hall.domain.Work;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryWorkRepositoryTest {

    MemoryWorkRepository memoryWorkRepository = new MemoryWorkRepository();

    @AfterEach
    void afterEach(){
        memoryWorkRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Work work1 = VideoWork.builder()
                .genreIds(List.of(WorkGenreConst.ACTION))
                .subCategory(WorkSubcategoryConst.MOVIE)
                .categoryId(WorkCategoryConst.VIDEO)
                .content("작품 내용1")
                .memberId(1L)
                .performers(List.of("배우1", "배우2"))
                .production("AAA 제작사")
                .rating(4.5f)
                .name("작품 이름1")
                .visibility(true)
                .year(2024)
                .build();
        Work work2 = VideoWork.builder()
                .genreIds(List.of(WorkGenreConst.ACTION))
                .subCategory(WorkSubcategoryConst.MOVIE)
                .categoryId(WorkCategoryConst.VIDEO)
                .content("작품 내용2")
                .memberId(1L)
                .performers(List.of("배우1", "배우2"))
                .production("BBB 제작사")
                .rating(3.5f)
                .name("작품 이름2")
                .visibility(true)
                .year(2024)
                .build();
        memoryWorkRepository.save(work1);
        memoryWorkRepository.save(work2);

        //when
        List<Work> findAll = memoryWorkRepository.findAll();
        Work findById = memoryWorkRepository.findById(work1.getId());

        //then
        assertThat(findAll.size()).isEqualTo(2);
        assertThat(findAll.getFirst()).isInstanceOf(VideoWork.class);
        assertThat(findById.getName()).isEqualTo(work1.getName());
    }
}