package com.mymosaic.hall.repository;

import com.mymosaic.common.constant.SortConst;
import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.domain.TextWork;
import com.mymosaic.hall.domain.VideoWork;
import com.mymosaic.hall.domain.Work;
import com.mymosaic.hall.dto.TextWorkEditParam;
import com.mymosaic.hall.dto.VideoWorkEditParam;
import com.mymosaic.hall.dto.WorkEditParam;
import com.mymosaic.hall.dto.WorkSearchAndSortParam;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MemoryWorkRepository implements WorkRepository{

    private static Map<Long, Work> store = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    @Override
    public void save(Work work) {
        work.setId(++sequence);
        store.put(work.getId(), work);
        log.info("save work {}", work);
    }

    @Override
    public Work findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Work> findByMemberId(Long memberId, WorkSearchAndSortParam param) {
        return findAll().stream()
                .filter(w -> w.getMemberId().equals(memberId))
                .filter(w -> {
                    boolean matches = true;
                    if (param.getKeyword() != null && !param.getKeyword().isEmpty()) { //검색 키워드가 존재하는 경우
                        matches = w.getName().toLowerCase().contains(param.getKeyword().toLowerCase()); //대소문자 구분x
                    }
                    if (param.getCategoryId() != null) {
                        matches =  matches && w.getCategoryId().equals(param.getCategoryId());
                        if(matches){
                            matches = matches && getCategoryMatches(w, param, matches);
                        }
                    }
                    return matches;
                })
                .sorted(getComparator(param.getSortBy(), param.getSortDir()))
                .toList();
    }

    private Boolean getCategoryMatches(Work w, WorkSearchAndSortParam param, Boolean matches){
        switch ( w.getCategoryId()) {
            case WorkCategoryConst.VIDEO -> {
                VideoWork videoWork = (VideoWork) w;
                if(param.getSubcategoryId() != null){
                    matches = matches && param.getSubcategoryId().equals(videoWork.getSubCategoryId());
                }
                if(param.getYear() != null){
                    matches = matches && param.getYear().equals(videoWork.getYear());
                }
                if(param.getGenreIds() != null && !param.getGenreIds().isEmpty()){
                    for (Integer genre : param.getGenreIds()) {
                        matches = matches && videoWork.getGenreIds().contains(genre);
                    }
                }
            }
            case WorkCategoryConst.TEXT -> {
                TextWork textWork = (TextWork) w;
                if (param.getSubcategoryId() != null) {
                    matches = matches && param.getSubcategoryId().equals(textWork.getSubCategoryId());
                }
                if (param.getYear() != null) {
                    matches = matches && param.getYear().equals(textWork.getYear());
                }
                if (param.getGenreIds() != null && !param.getGenreIds().isEmpty()) {
                    for (Integer genre : param.getGenreIds()) {
                        matches = matches && textWork.getGenreIds().contains(genre);
                    }
                }
            }
        }
        return matches;
    }
    private Comparator<Work> getComparator(String sortBy, String sortDir) {
        Comparator<Work> comparator = switch (sortBy) {
            case SortConst.RATING -> Comparator.comparing(Work::getRating);
            case SortConst.NAME -> Comparator.comparing(Work::getName);
            default -> Comparator.comparing(Work::getCreatedTime);
        };

        if ("desc".equalsIgnoreCase(sortDir)) {
            comparator = comparator.reversed();
        }

        return comparator;
    }

    @Override
    public List<Work> findAll(){
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long id, Integer categoryId, WorkEditParam param) {
        Work newWork = null;
        switch (categoryId) {
            case WorkCategoryConst.VIDEO -> {
                VideoWork work = (VideoWork) findById(id);
                VideoWorkEditParam p = (VideoWorkEditParam) param;
                work.updateVideoWorkInfo(p.getVisibility(), p.getName(), p.getContent(), p.getRating(),
                        p.getSubCategoryId(), p.getGenreIds(), p.getWorkImageFiles(), p.getProduction(),
                        p.getPerformers(), p.getYear());
                newWork = work;
            }
            case WorkCategoryConst.TEXT -> {
                TextWork work = (TextWork) findById(id);
                TextWorkEditParam p = (TextWorkEditParam) param;
                work.updateTextWorkInfo(p.getVisibility(), p.getName(), p.getContent(), p.getRating(),
                        p.getSubCategoryId(), p.getGenreIds(), p.getWorkImageFiles(), p.getPublisher(),
                        p.getAuthors(), p.getYear());
                newWork = work;
            }
        }
        if(newWork != null){
            store.put(id, newWork);
        }
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    public void clearStore(){
        store.clear();
    }
}
