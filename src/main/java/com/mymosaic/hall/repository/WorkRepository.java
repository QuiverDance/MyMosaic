package com.mymosaic.hall.repository;


import com.mymosaic.hall.domain.Work;
import com.mymosaic.hall.dto.WorkEditParam;
import com.mymosaic.hall.dto.WorkSearchAndSortParam;

import java.util.List;

public interface WorkRepository {

    void save(Work work);
    Work findById(Long id);
    List<Work> findByMemberId(Long memberId, WorkSearchAndSortParam param);
    List<Work> findAll();
    void update(Long id, Integer categoryId, WorkEditParam param);
    void delete(Long id);
}
