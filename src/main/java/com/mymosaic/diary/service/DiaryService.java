package com.mymosaic.diary.service;

import com.mymosaic.common.constant.FileDirConst;
import com.mymosaic.common.file.FileManger;
import com.mymosaic.common.file.UploadFile;
import com.mymosaic.diary.dto.DiaryDto;
import com.mymosaic.diary.repository.DiaryRepository;
import com.mymosaic.diary.web.DiaryAddForm;
import com.mymosaic.diary.web.DiaryEditForm;
import com.mymosaic.diary.web.SearchAndSortForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final FileManger fileManger;

    public void saveDiary(DiaryAddForm form, Long memberId) throws IOException {
        List<UploadFile> attachFiles = fileManger.storeFiles(FileDirConst.DIARY_DIR, form.getFiles());
        diaryRepository.save(form.toDiary(memberId, attachFiles));
    }

    public DiaryDto findDairyById(Long id) throws IOException {
        DiaryDto diaryDto = new DiaryDto(diaryRepository.findById(id));
        diaryDto.setEncodedFiles(fileManger.loadImages(diaryDto.getFiles()));
        return diaryDto;
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
