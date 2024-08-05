package com.mymosaic.common.controller;

import com.mymosaic.member.dto.MemberDto;
import com.mymosaic.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping
    public String home(@CookieValue(name = "memberId", required = false) Long memberId,
                       Model model){
        if(memberId == null){
            return "home";
        }

        MemberDto member = memberService.findMemberById(memberId);
        if(member == null){
            return "home";
        }
        model.addAttribute("member", member);
        return "loginHome";
    }
}
