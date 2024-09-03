package com.mymosaic.hall.service;

import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.domain.VideoWork;
import com.mymosaic.hall.domain.Work;
import com.mymosaic.hall.repository.WorkRepository;
import com.mymosaic.hall.web.VideoWorkAddForm;
import com.mymosaic.hall.web.WorkAddForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;

    public void saveWork(WorkAddForm workAddForm, Long memberId){
        Work work = null;
        if(workAddForm.getCategoryId().equals(WorkCategoryConst.VIDEO)){
            VideoWorkAddForm form = (VideoWorkAddForm) workAddForm;
            work = createVideoWork(form, memberId);
        }
        else{
            throw new IllegalArgumentException("Invalid categoryId");
        }
        workRepository.save(work);
    }

    private Work createVideoWork(VideoWorkAddForm form, Long memberId){
        Work videoWork = VideoWork.builder()
                .memberId(memberId)
                .categoryId(form.getCategoryId())
                .visibility(form.getVisibility())
                .name(form.getName())
                .content(form.getContent())
                .rating(form.getRating())
                .subCategory(form.getSubCategory())
                .genreIds(form.getGenreIds())
                .production(form.getProduction())
                .performers(form.getPerformers())
                .year(form.getYear())
                .build();
        return videoWork;
    }
}
