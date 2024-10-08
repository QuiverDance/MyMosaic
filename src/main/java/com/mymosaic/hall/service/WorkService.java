package com.mymosaic.hall.service;

import com.mymosaic.common.constant.FileDirConst;
import com.mymosaic.common.file.FileManger;
import com.mymosaic.common.file.UploadFile;
import com.mymosaic.hall.common.WorkTypeConverter;
import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.domain.VideoWork;
import com.mymosaic.hall.domain.Work;
import com.mymosaic.hall.dto.WorkDto;
import com.mymosaic.hall.dto.WorkSearchAndSortParam;
import com.mymosaic.hall.repository.WorkRepository;
import com.mymosaic.hall.web.WorkAddForm;
import com.mymosaic.hall.web.WorkEditForm;
import com.mymosaic.hall.web.WorkSearchAndSortForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;
    private final WorkTypeConverter workTypeConverter;
    private final FileManger fileManger;


    public void saveWork(WorkAddForm form, Long memberId) throws IOException {
        workRepository.save(workTypeConverter.convertToWork(form, memberId));
    }

    public WorkDto findWorkById(Long id) throws IOException {
        return workTypeConverter.convertToDto(workRepository.findById(id));
    }

    public List<WorkDto> findWorksByMemberId(Long memberId, WorkSearchAndSortForm form){
        return workRepository.findByMemberId(memberId, form.toParam())
                .stream().map(w -> {
                    try {
                        return workTypeConverter.convertToDto(w);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
    }

    public void editWork(WorkEditForm form, Long workId, Integer categoryId) throws IOException {
        switch (categoryId){
            case WorkCategoryConst.VIDEO -> {
                List<UploadFile> uploadFiles = fileManger.storeFiles(FileDirConst.WORK_DIR, form.getWorkImageFiles());
                workRepository.update(workId, categoryId, form.toVideoEditParam(uploadFiles));
            }
            case WorkCategoryConst.TEXT -> {
                List<UploadFile> uploadFiles = fileManger.storeFiles(FileDirConst.WORK_DIR, form.getWorkImageFiles());
                workRepository.update(workId, categoryId, form.toTextEditParam(uploadFiles));
            }
        }
    }

    public void deleteWork(Long workId){
        workRepository.delete(workId);
    }
}
