package com.mymosaic.diary.repository;

import com.mymosaic.diary.domain.Diary;
import com.mymosaic.diary.dto.DiaryEditParam;
import com.mymosaic.diary.dto.DiarySearchParam;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MemoryDiaryRepository implements DiaryRepository{

    private static Map<Long, Diary> store = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    @Override
    public void save(Diary diary) {
        diary.setId(++sequence);
        store.put(diary.getId(), diary);
        log.info("save diary {}", diary);
    }

    @Override
    public Diary findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Diary> findByMemberId(Long memberId, DiarySearchParam param) {
        return findAll().stream()
                .filter(d -> d.getMemberId().equals(memberId))
                .toList();
    }

    private List<Diary> findAll(){
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long id, DiaryEditParam param) {
        Diary diary = findById(id);
        diary.updateDiaryInfo(
                param.getIsPublic(),
                param.getTitle(),
                param.getContent(),
                param.getFiles(),
                param.getDiaryDate());
    }

    @Override
    public void updateBookmark(Long id, Boolean bookmark) {
        Diary diary = findById(id);
        diary.updateBookmark(bookmark);
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    public void clearStore(){
        store.clear();
    }
}
