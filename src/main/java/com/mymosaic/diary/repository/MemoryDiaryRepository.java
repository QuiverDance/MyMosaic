package com.mymosaic.diary.repository;

import com.mymosaic.diary.domain.Diary;
import com.mymosaic.diary.dto.DiaryEditParam;
import com.mymosaic.diary.dto.DiarySearchAndSortParam;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
    public List<Diary> findByMemberId(Long memberId, DiarySearchAndSortParam param) {

        return findAll().stream()
                .filter(d -> d.getMemberId().equals(memberId)) //해당 memberId의 모든 일기를 가져옴
                .filter(d -> {
                    boolean matches = true;
                    if (param.getKeyword() != null && !param.getKeyword().isEmpty()) { //검색 키워드가 존재하는 경우
                        matches = d.getTitle().toLowerCase().contains(param.getKeyword().toLowerCase()); //대소문자 구분x
                    }
                    if (param.getYear() != null) {
                        matches = matches && d.getDiaryDate().getYear() == param.getYear();
                    }
                    if (param.getMonth() != null) {
                        matches = matches && d.getDiaryDate().getMonthValue() == param.getMonth();
                    }
                    return matches;
                })
                .sorted(getComparator(param.getSortBy(), param.getSortDir()))
                .toList();
    }

    private Comparator<Diary> getComparator(String sortBy, String sortDir) {
        Comparator<Diary> comparator = switch (sortBy) {
            case "createdTime" -> Comparator.comparing(Diary::getCreatedTime);
            case "title" -> Comparator.comparing(Diary::getTitle);
            default -> Comparator.comparing(Diary::getDiaryDate);
        };

        if ("desc".equalsIgnoreCase(sortDir)) {
            comparator = comparator.reversed();
        }

        return comparator;
    }

    @Override
    public List<Diary> findAll(){
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
    public void delete(Long id) {
        store.remove(id);
    }

    public void clearStore(){
        store.clear();
    }
}
