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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    /*
     * 회원가입 화면 요청
     * */
    @GetMapping("/add")
    public String addForm(@ModelAttribute("form") RegisterForm form) {
        return "members/addMemberForm";
    }

    /*
     * 전달한 회원가입 form을 바탕으로 회원 정보 저장
     * */
    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("form") RegisterForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/addMemberForm";
        }
        MemberDto memberByLoginId = memberService.findMemberByLoginId(form.getLoginId());
        if(memberByLoginId != null){
            result.addError(new FieldError("form", "loginId", "중복 아이디 입니다."));
            return "members/addMemberForm";
        }
        memberService.registerMember(form);
        return "redirect:/";
    }


    /*
     * 회원 정보 화면 요청
     * */
    @GetMapping("/{memberId}")
    public String MemberInfo(@PathVariable("memberId") Long memberId, Model model){
        MemberDto member = memberService.findMemberById(memberId);
        model.addAttribute("member", member);
        return "members/myInfo";
    }

    /*
     * 회원 수정 화면 요청
     * */
    @GetMapping("/{memberId}/edit")
    public String editMemberInfo(@PathVariable("memberId") Long memberId, Model model) {
        MemberDto member = memberService.findMemberById(memberId);
        model.addAttribute("member", member);
        return "members/editMemberForm";
    }

    /*
     * 전달한 form을 바탕으로 회원 정보 수정
     * */
    @PatchMapping("/{memberId}/edit")
    public String editMemberInfo(@PathVariable("memberId") Long memberId, @ModelAttribute("member") MemberInfoEditForm form){
        memberService.updateMemberInfo(memberId, form);
        return "redirect:/members/{memberId}";
    }
}
