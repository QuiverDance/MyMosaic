package com.mymosaic.member.interceptor;

import com.mymosaic.common.constant.SessionConst;
import com.mymosaic.member.dto.MemberDto;
import com.mymosaic.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("login check interceptor start {}", requestURI);
        HttpSession session = request.getSession();

        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
            log.info("Unauthenticated user access");
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }

        MemberDto accessMember = (MemberDto)session.getAttribute(SessionConst.LOGIN_MEMBER);
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(requestURI);
        if(!matcher.find()){
            log.info("invalid url");
            response.sendRedirect("/");
            return false;
        }

        log.info("list : {} ", matcher.group());
        Long targetMemberId = Long.parseLong(matcher.group(0));
        log.info("targetMemberId {}", targetMemberId);
        MemberDto targetMember = memberService.findMemberById(targetMemberId);

        if(!accessMember.getId().equals(targetMember.getId())){
            log.info("unAuthorized user access");
            response.sendRedirect(request.getHeader("Referer"));
            return false;
        }
        return true;
    }
}
