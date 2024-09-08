package com.mymosaic.hall.controller;

import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.dto.WorkDto;
import com.mymosaic.hall.service.WorkService;
import com.mymosaic.hall.web.WorkAddForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/works/{memberId}")
public class WorkController {

    private final WorkService workService;

    @GetMapping("/add")
    public String getCategoryForm(@PathVariable("memberId") Long memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "works/selectCategoryForm";
    }

    @PostMapping("/add/category")
    public String showFormByCategory(@PathVariable("memberId") Long memberId,
                                     @ModelAttribute("addForm") WorkAddForm addForm,
                                     @RequestParam("categoryId") Integer categoryId,
                                     Model model) {
        if (categoryId.equals(WorkCategoryConst.VIDEO)){
            model.addAttribute("categoryId", categoryId);
            return "works/fragments/videoWorkAddFormFragment :: formFields";
        }
        else{
            throw new IllegalArgumentException("Invalid category");
        }
    }

    @PostMapping("/add")
    public String addWork(@PathVariable("memberId") Long memberId,
                          @RequestParam("categoryId") Integer categoryId,
                          @ModelAttribute("addForm") WorkAddForm addForm) {
        addForm.setCategoryId(categoryId);
        workService.saveWork(addForm, memberId);
        return "redirect:/works/{memberId}";
    }

    @GetMapping
    public String getWorks(@PathVariable("memberId") Long memberId,
                           Model model){
        List<WorkDto> works = workService.findWorksByMemberId(memberId);
        log.info("works = {}", works.size());

        model.addAttribute("works", works);
        return "works/workList";
    }

    @GetMapping("/{workId}")
    public String getWorkInfo(@PathVariable("memberId") Long memberId,
                              @PathVariable("workId") Long workId,
                              Model model) {

        WorkDto work = workService.findWorkById(workId);
        model.addAttribute("work", work);

        return "works/workInfo";
    }
}
