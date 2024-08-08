package com.mymosaic.member.controller;

import com.mymosaic.member.dto.MemberDto;
import com.mymosaic.member.dto.MemberInfoEditForm;
import com.mymosaic.member.dto.RegisterForm;
import com.mymosaic.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("form") RegisterForm form) {
        return "members/addMemberForm";
    }
    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("form") RegisterForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/addMemberForm";
        }
        memberService.registerMember(form);
        return "redirect:/";
    }

    @GetMapping("/{memberId}")
    public String MemberInfo(@PathVariable("memberId") Long memberId, Model model){
        MemberDto member = memberService.findMemberById(memberId);
        model.addAttribute("member", member);
        return "members/myInfo";
    }

    @GetMapping("/{memberId}/edit")
    public String editMemberInfo(@PathVariable("memberId") Long memberId, Model model) {
        MemberDto member = memberService.findMemberById(memberId);
        model.addAttribute("member", member);
        return "members/editMemberForm";
    }

    @PatchMapping("/{memberId}/edit")
    public String editMemberInfo(@PathVariable("memberId") Long memberId, @ModelAttribute("member") MemberInfoEditForm form, Model model){

        MemberDto updatedMember = memberService.updateMemberInfo(memberId, form);

        MemberDto findMember = memberService.findMemberById(memberId);
        System.out.println("find member intro : " + findMember.getIntroduction());
        model.addAttribute("member", updatedMember);
        return "redirect:/members/{memberId}";
    }
}
