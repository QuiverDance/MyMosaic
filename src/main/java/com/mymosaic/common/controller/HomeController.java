package com.mymosaic.common.controller;

import com.mymosaic.common.constant.SessionConst;
import com.mymosaic.member.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

    @GetMapping
    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberDto member,
                       Model model){

        /*세션에 member가 없는 경우 -> 일반 home 화면*/
        if(member == null){
            return "index";
        }
        /*세션에 member가 있는 경우 -> 로그인 home 화면*/
        model.addAttribute("member", member);
        return "loginIndex";
    }
}
