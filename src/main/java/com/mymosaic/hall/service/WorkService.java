package com.mymosaic.hall.service;

import com.mymosaic.hall.common.WorkTypeConverter;
import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.domain.VideoWork;
import com.mymosaic.hall.domain.Work;
import com.mymosaic.hall.dto.WorkDto;
import com.mymosaic.hall.dto.WorkSearchAndSortParam;
import com.mymosaic.hall.repository.WorkRepository;
import com.mymosaic.hall.web.WorkAddForm;
import com.mymosaic.hall.web.WorkEditForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;

    public void saveWork(WorkAddForm form, Long memberId){
        Work work = null;
        if(form.getCategoryId().equals(WorkCategoryConst.VIDEO)){
            work = createVideoWork(form, memberId);
        }
        else{
            throw new IllegalArgumentException("Invalid categoryId");
        }
        workRepository.save(work);
    }

    public WorkDto findWorkById(Long id){
        return WorkTypeConverter.convertToDto(workRepository.findById(id));
    }

    public List<WorkDto> findWorksByMemberId(Long memberId){
        return workRepository.findByMemberId(memberId, new WorkSearchAndSortParam())
                .stream().map(WorkTypeConverter::convertToDto).toList();
    }

    public void editWork(WorkEditForm form, Long memberId, Integer categoryId){
        if(categoryId.equals(WorkCategoryConst.VIDEO))
            workRepository.update(memberId, categoryId, form.toVideoEditParam());
    }
    private Work createVideoWork(WorkAddForm form, Long memberId){
        Work videoWork = VideoWork.builder()
                .memberId(memberId)
                .categoryId(form.getCategoryId())
                .visibility(form.getVisibility())
                .name(form.getName())
                .content(form.getContent())
                .rating(form.getRating())
                .subCategoryId(form.getSubCategoryId())
                .genreIds(form.getGenreIds())
                .production(form.getProduction())
                .performers(form.getPerformers())
                .year(form.getYear())
                .build();
        return videoWork;
    }
}
