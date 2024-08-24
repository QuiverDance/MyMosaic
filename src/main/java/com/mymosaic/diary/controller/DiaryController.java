package com.mymosaic.diary.controller;

import com.mymosaic.diary.dto.DiaryDto;
import com.mymosaic.diary.service.DiaryService;
import com.mymosaic.diary.web.DiaryAddForm;
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
@RequestMapping("/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/{memberId}/add")
    String getAddForm(@PathVariable("memberId") Long memberId,
                      @ModelAttribute("form") DiaryAddForm form,
                      Model model){
        model.addAttribute("memberId", memberId);
        return "diaries/addDiaryForm";
    }

    @PostMapping("/{memberId}/add")
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

    @GetMapping("/{memberId}")
    String getDiaries(@PathVariable("memberId") Long memberId, Model model){
        List<DiaryDto> diaries = diaryService.findDiaryByMemberId(memberId, null); //Param search is not implemented
        model.addAttribute("diaries", diaries);
        model.addAttribute("memberId", memberId);
        return "diaries/diaryList";
    }
}
