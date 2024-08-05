package com.mymosaic.member.controller;

import com.mymosaic.common.constant.SessionConst;
import com.mymosaic.member.dto.LoginForm;
import com.mymosaic.member.dto.MemberDto;
import com.mymosaic.member.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("form")LoginForm form){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("form") LoginForm form,
                        BindingResult bindingResult,
                        HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        MemberDto member = loginService.login(form.getLoginId(), form.getPassword());
        if(member == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        /*기존 세션 반환 없으면 새로 생성(default)*/
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);

        log.info("login success : {}", member.getLoginId());
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){

        /*세션이 없어도 새로 생성 안함*/
        HttpSession session = request.getSession(false);
        if(session != null)
            session.invalidate();

        return "redirect:/";
    }
}
