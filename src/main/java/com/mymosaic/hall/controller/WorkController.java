package com.mymosaic.hall.controller;

import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.domain.Work;
import com.mymosaic.hall.dto.WorkDto;
import com.mymosaic.hall.service.WorkService;
import com.mymosaic.hall.web.WorkAddForm;
import com.mymosaic.hall.web.WorkEditForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
                          @ModelAttribute("addForm") WorkAddForm addForm) throws IOException {
        addForm.setCategoryId(categoryId);
        workService.saveWork(addForm, memberId);
        return "redirect:/works/{memberId}";
    }

    @GetMapping
    public String getWorks(@PathVariable("memberId") Long memberId,
                           @RequestAttribute("isOwner") Boolean isOwner,
                           Model model) throws IOException {
        List<WorkDto> works = workService.findWorksByMemberId(memberId);
        log.info("works = {}", works.size());

        model.addAttribute("works", works);
        model.addAttribute("isOwner", isOwner);
        return "works/workList";
    }

    @GetMapping("/{workId}")
    public String getWorkInfo(@PathVariable("memberId") Long memberId,
                              @RequestAttribute("isOwner") Boolean isOwner,
                              @PathVariable("workId") Long workId,
                              Model model) throws IOException {
        WorkDto work = workService.findWorkById(workId);

        model.addAttribute("work", work);
        model.addAttribute("isOwner", isOwner);
        return "works/workInfo";
    }

    @GetMapping("/{workId}/edit")
    public String getEditForm(@PathVariable("memberId") Long memberId,
                              @PathVariable("workId") Long workId,
                              @RequestAttribute("categoryId") Integer categoryId,
                              Model model) throws IOException {
        WorkDto work = workService.findWorkById(workId);
        WorkEditForm form = new WorkEditForm(work, categoryId);
        model.addAttribute("editForm", form);
        model.addAttribute("categoryId", categoryId);
        return "works/editWorkForm";
    }

    @PatchMapping("/{workId}/edit")
    public String editWork(@PathVariable("memberId") Long memberId,
                           @PathVariable("workId") Long workId,
                           @RequestAttribute("categoryId") Integer categoryId,
                           @Valid @ModelAttribute("editForm") WorkEditForm editForm,
                           BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            return "works/editWorkForm";
        }
        log.info("edit workID = {}", workId);
        workService.editWork(editForm, workId, categoryId);
        return "redirect:/works/{memberId}/{workId}";
    }

    @DeleteMapping("/{workId}/delete")
    public String deleteWork(@PathVariable("memberId") Long memberId,
                             @PathVariable("workId") Long workId){
        workService.deleteWork(workId);
        return "redirect:/works/{memberId}";
    }
}
