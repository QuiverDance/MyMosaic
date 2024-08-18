package com.mymosaic.member.controller;

import com.mymosaic.common.constant.SessionConst;
import com.mymosaic.member.web.LoginForm;
import com.mymosaic.member.dto.MemberDto;
import com.mymosaic.member.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
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

    /*
    * 로그인 화면 요청
    * */
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("form")LoginForm form){
        return "login/loginForm";
    }

    /*
    * 전달받은 form으로 로그인 시도
    * */
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("form") LoginForm form,
                        BindingResult bindingResult,
                        HttpServletRequest request){
        /*입력 값에 문제가 있는 경우 -> 다시 로그인 화면 불러오기*/
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        /*입력 로그인 아이디와 비밀번호로 로그인 시도, 실패시 null 반환*/
        MemberDto member = loginService.login(form.getLoginId(), form.getPassword());
        if(member == null){
            bindingResult.reject("loginFail");
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
            session.invalidate(); //세션 만료

        return "redirect:/";
    }
}
