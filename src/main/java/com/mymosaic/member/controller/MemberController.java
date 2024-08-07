package com.mymosaic.member.controller;

import com.mymosaic.common.constant.SessionConst;
import com.mymosaic.member.dto.MemberDto;
import com.mymosaic.member.dto.MemberInfoForm;
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

    @GetMapping("/myInfo")
    public String getMyInfo(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberDto member,
                            Model model){
        if(member == null){
            return "home";
        }

        model.addAttribute("member", member);
        return "members/myInfo";
    }

    @GetMapping("/update")
    public String addForm(@ModelAttribute("form") MemberInfoForm form) {
        return "members/updateMemberForm";
    }
    @PostMapping("/update")
    public String updateMyInfo(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberDto member,
                               @ModelAttribute("form") MemberInfoForm form, Model model){
        if(member == null){
            return "home";
        }

        MemberDto updatedMember = memberService.updateMemberInfo(member.getId(), form);
        System.out.println("updated member : " + updatedMember);
        model.addAttribute("member", updatedMember);
        return "members/myInfo";
    }
}
