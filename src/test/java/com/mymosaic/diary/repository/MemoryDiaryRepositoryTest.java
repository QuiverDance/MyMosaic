package com.mymosaic.diary.repository;

import com.mymosaic.diary.domain.Diary;
import com.mymosaic.diary.dto.DiaryEditParam;
import com.mymosaic.diary.dto.DiarySearchAndSortParam;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryDiaryRepositoryTest {

    private final MemoryDiaryRepository diaryRepository = new MemoryDiaryRepository();

    @AfterEach
    void afterEach(){
        diaryRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Diary diary1 = new Diary(1L, false, "제목 1","사용자 1의 일기1", new ArrayList<>(), LocalDate.now());
        Diary diary2 = new Diary(1L, false, "제목 2","사용자 1의 일기2", new ArrayList<>(), LocalDate.now());
        Diary diary3 = new Diary(1L, false, "제목 3","사용자 1의 일기3", new ArrayList<>(), LocalDate.now());
        Diary diary4 = new Diary(2L, false, "제목 11","사용자 2의 일기1", new ArrayList<>(), LocalDate.now());
        Diary diary5 = new Diary(3L, false, "제목 22","사용자 3의 일기1", new ArrayList<>(), LocalDate.now());

        //when
        diaryRepository.save(diary1);
        diaryRepository.save(diary2);
        diaryRepository.save(diary3);
        diaryRepository.save(diary4);
        diaryRepository.save(diary5);

        Diary findDairy4 = diaryRepository.findById(diary4.getId());
        List<Diary> findDiaryList = diaryRepository.findByMemberId(1L, new DiarySearchAndSortParam());

        //then
        assertThat(findDairy4.getMemberId()).isEqualTo(2L);
        assertThat(findDiaryList.size()).isEqualTo(3);
    }

    @Test
    void delete(){
        //given
        Diary diary1 = new Diary(1L, false, "제목 1","사용자 1의 일기1", new ArrayList<>(), LocalDate.now());
        Diary diary2 = new Diary(1L, false, "제목 2","사용자 1의 일기2", new ArrayList<>(), LocalDate.now());
        Diary diary3 = new Diary(1L, false, "제목 3","사용자 1의 일기3", new ArrayList<>(), LocalDate.now());

        //when
        diaryRepository.save(diary1);
        diaryRepository.save(diary2);
        diaryRepository.save(diary3);

        diaryRepository.delete(diary2.getId());
        List<Diary> findDiaryList = diaryRepository.findByMemberId(1L,  new DiarySearchAndSortParam());

        //then
        assertThat(findDiaryList.size()).isEqualTo(2);
    }

    @Test
    void update(){
        //given
        Diary diary1 = new Diary(1L, false, "제목 1", "사용자 1의 일기1", new ArrayList<>(), LocalDate.now());

        //when
        diaryRepository.save(diary1);
        DiaryEditParam param = new DiaryEditParam();
        param.setIsPublic(true);
        param.setTitle("변경된 제목");
        param.setContent("수정된 사용자 1의 일기1");
        param.setDiaryDate(LocalDate.of(2024, 1, 1));

        diaryRepository.update(diary1.getId(), param);
        Diary findDiary = diaryRepository.findById(diary1.getId());
        //then
        assertThat(findDiary).isEqualTo(diary1);
    }
}