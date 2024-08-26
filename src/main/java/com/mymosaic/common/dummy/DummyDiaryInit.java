package com.mymosaic.common.dummy;

import com.mymosaic.diary.domain.Diary;
import com.mymosaic.diary.repository.DiaryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DummyDiaryInit {

    private final DiaryRepository diaryRepository;

    @PostConstruct
    public void init(){
        Diary diary1 = new Diary(1L, true, "일기 1", "일기1의 내용", LocalDate.now());
        Diary diary2 = new Diary(1L, false, "일기 2", "일기2의 내용", LocalDate.now());
        Diary diary3 = new Diary(1L, true, "일기 3", "일기3의 내용", LocalDate.now());
        Diary diary4 = new Diary(2L, true, "일기 4", "일기4의 내용", LocalDate.now());
        Diary diary5 = new Diary(2L, true, "일기 5", "일기5의 내용", LocalDate.now());
        Diary diary6 = new Diary(2L, true, "일기 6", "일기6의 내용", LocalDate.now());

        diaryRepository.save(diary1);
        diaryRepository.save(diary2);
        diaryRepository.save(diary3);
        diaryRepository.save(diary4);
        diaryRepository.save(diary5);
        diaryRepository.save(diary6);
    }
}
