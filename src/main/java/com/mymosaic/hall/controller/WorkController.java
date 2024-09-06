package com.mymosaic.hall.controller;

import com.mymosaic.hall.dto.WorkDto;
import com.mymosaic.hall.service.WorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/works/{memberId}")
public class WorkController {

    private final WorkService workService;

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

        WorkDto work = workService.findWorkById(memberId);
        model.addAttribute("work", work);

        return "works/workInfo";  // workDetail.html 템플릿으로 반환
    }
}
