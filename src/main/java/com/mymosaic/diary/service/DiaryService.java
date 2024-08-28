package com.mymosaic.diary.service;

import com.mymosaic.common.file.UploadFile;
import com.mymosaic.diary.dto.DiaryDto;
import com.mymosaic.diary.repository.DiaryRepository;
import com.mymosaic.diary.web.DiaryAddForm;
import com.mymosaic.diary.web.DiaryEditForm;
import com.mymosaic.diary.web.SearchAndSortForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public void saveDiary(DiaryAddForm form, List<UploadFile> files, Long memberId){
        diaryRepository.save(form.toDiary(memberId, files));
    }

    public DiaryDto findDairyById(Long id){
        return new DiaryDto(diaryRepository.findById(id));
    }

    public List<DiaryDto> findDiaryByMemberId(Long memberId, SearchAndSortForm form){
        return diaryRepository.findByMemberId(memberId, form.toParam())
                .stream().map(DiaryDto::new).toList();
    }

    public void updateDiaryInfo(Long id, DiaryEditForm form){
        diaryRepository.update(id, form.toEditParam());
    }

    public void deleteDiary(Long id){
        diaryRepository.delete(id);
    }
}
