package com.mymosaic.diary.controller;

import com.mymosaic.diary.dto.DiaryDto;
import com.mymosaic.diary.dto.DiaryEditParam;
import com.mymosaic.diary.service.DiaryService;
import com.mymosaic.diary.web.DiaryAddForm;
import com.mymosaic.diary.web.DiaryEditForm;
import com.mymosaic.diary.web.SearchAndSortForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/diaries/{memberId}")
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/add")
    String getAddForm(@PathVariable("memberId") Long memberId,
                      @ModelAttribute("form") DiaryAddForm form,
                      Model model){
        model.addAttribute("memberId", memberId);
        return "diaries/addDiaryForm";
    }

    @PostMapping("/add")
    String addDiary(@PathVariable("memberId") Long memberId,
                    @ModelAttribute("form") DiaryAddForm form,
                    BindingResult bindingResult){
        log.info("addDiary start");
        if(bindingResult.hasErrors()){
            log.info("diary error = {}", bindingResult.getAllErrors());
            return "diaries/addDiaryForm";
        }
        diaryService.saveDiary(form, memberId);
        return "redirect:/diaries/{memberId}";
    }

    @GetMapping
    String getDiaries(@PathVariable("memberId") Long memberId,
                      @ModelAttribute("form") SearchAndSortForm form,
                      @RequestAttribute("isOwner") Boolean isOwner,
                      Model model){

        List<DiaryDto> diaries = diaryService.findDiaryByMemberId(memberId, form);
        model.addAttribute("diaries", diaries);
        model.addAttribute("memberId", memberId);
        model.addAttribute("isOwner", isOwner);
        return "diaries/diaryList";
    }

    @GetMapping("/{diaryId}")
    String getDiaryInfo(@PathVariable("memberId") Long memberId,
                        @PathVariable("diaryId") Long diaryId,
                        @RequestAttribute("isOwner") Boolean isOwner,
                        Model model){
        DiaryDto diary = diaryService.findDairyById(diaryId);
        model.addAttribute("memberId", memberId);
        model.addAttribute("diary", diary);
        model.addAttribute("isOwner", isOwner);
        return "diaries/diaryInfo";
    }

    @DeleteMapping("/{diaryId}/delete")
    String deleteDiary(@PathVariable("memberId") Long memberId,
                       @PathVariable("diaryId") Long diaryId,
                       Model model){
        diaryService.deleteDiary(diaryId);
        model.addAttribute(memberId);
        return "redirect:/diaries/{memberId}";
    }

    @GetMapping("/{diaryId}/edit")
    String getEditForm(@PathVariable("memberId") Long memberId,
                       @PathVariable("diaryId") Long diaryId,
                       @ModelAttribute("form") DiaryEditParam form,
                       Model model){
        DiaryDto diary = diaryService.findDairyById(diaryId);
        form.setIsPublic(diary.getIsPublic());
        form.setTitle(diary.getTitle());
        form.setContent(diary.getContent());
        form.setDiaryDate(diary.getDiaryDate());

        model.addAttribute("memberId", memberId);
        model.addAttribute("diaryId", diaryId);
        return "diaries/editDiaryForm";
    }

    @PatchMapping("/{diaryId}/edit")
    String editDiary(@PathVariable("memberId") Long memberId,
                     @PathVariable("diaryId") Long diaryId,
                     @ModelAttribute("form") DiaryEditForm form,
                     BindingResult bindingResult,
                     Model model){
        if(bindingResult.hasErrors()){
            return "diaries/editDiaryForm";
        }
        diaryService.updateDiaryInfo(diaryId, form);

        model.addAttribute("memberId", memberId);
        model.addAttribute("diaryId", diaryId);
        return "redirect:/diaries/{memberId}/{diaryId}";
    }
}
