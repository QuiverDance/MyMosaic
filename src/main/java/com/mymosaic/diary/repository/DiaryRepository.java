package com.mymosaic.diary.repository;

import com.mymosaic.diary.domain.Diary;
import com.mymosaic.diary.dto.DiaryEditParam;
import com.mymosaic.diary.dto.DiarySearchAndSortParam;

import java.util.List;

public interface DiaryRepository {

    void save(Diary diary);
    Diary findById(Long id);
    List<Diary> findByMemberId(Long memberId, DiarySearchAndSortParam param);
    List<Diary> findAll();
    void update(Long id, DiaryEditParam param);
    void delete(Long id);
}
