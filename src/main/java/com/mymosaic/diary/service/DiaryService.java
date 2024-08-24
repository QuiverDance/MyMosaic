package com.mymosaic.diary.service;

import com.mymosaic.diary.dto.DiaryDto;
import com.mymosaic.diary.dto.DiaryEditParam;
import com.mymosaic.diary.dto.DiarySearchParam;
import com.mymosaic.diary.repository.DiaryRepository;
import com.mymosaic.diary.web.DiaryAddForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public void saveDiary(DiaryAddForm form, Long memberId){
        diaryRepository.save(form.toDiary(memberId));
    }

    public DiaryDto findDairyById(Long id){
        return new DiaryDto(diaryRepository.findById(id));
    }

    public List<DiaryDto> findDiaryByMemberId(Long memberId, DiarySearchParam param){
        return diaryRepository.findByMemberId(memberId, param)
                .stream().map(DiaryDto::new).toList();
    }

    public void updateDiaryInfo(Long id, DiaryEditParam param){
        diaryRepository.update(id, param);
    }

    public void updateBookmark(Long id, boolean bookmark){
        diaryRepository.updateBookmark(id, bookmark);
    }

    public void deleteDiary(Long id){
        diaryRepository.delete(id);
    }
}
