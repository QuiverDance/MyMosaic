package com.mymosaic.hall.repository;

import com.mymosaic.hall.domain.Work;
import com.mymosaic.hall.dto.WorkEditParam;
import com.mymosaic.hall.dto.WorkSearchAndSortParam;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
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
        log.info("save diary {}", work);
    }

    @Override
    public Work findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Work> findByMemberId(Long memberId, WorkSearchAndSortParam param) {
        return findAll().stream()
                .filter(w -> w.getMemberId().equals(memberId))
                .toList();
    }

    @Override
    public List<Work> findAll(){
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long id, WorkEditParam param) {

    }

    @Override
    public void delete(Long id) {

    }

    public void clearStore(){
        store.clear();
    }
}
