package com.mymosaic.diary.repository;

import com.mymosaic.diary.domain.Diary;
import com.mymosaic.diary.dto.DiaryEditParam;
import com.mymosaic.diary.dto.DiarySearchParam;

import java.util.List;

public interface DiaryRepository {

    void save(Diary diary);
    Diary findById(Long id);
    List<Diary> findByMemberId(Long memberId, DiarySearchParam param);
    void update(Long id, DiaryEditParam param);
    void updateBookmark(Long id, Boolean bookmark);
    void delete(Long id);
}
