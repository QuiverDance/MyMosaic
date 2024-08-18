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

    /*
    * 접근을 요청한 사용자가 해당 경로에 접근할 수 있는지 확인
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*로그 확인용 요청 uri 저장*/
        String requestURI = request.getRequestURI();
        log.info("login check interceptor start {}", requestURI);
        /*현재 세션 가져오기*/
        HttpSession session = request.getSession();

        /*세션 또는 LOGIN_MEMBER 세션이 없는 경우 -> 로그인 화면으로 리다이렉트*/
        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
            log.info("Unauthenticated user access");
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }

        /*요청 uri 에서 숫자 추출({memberId} 부분)*/
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(requestURI);
        /*요청 uri 에서 숫자가 없는 경우 -> 홈 화면으로 리다이렉트*/
        if(!matcher.find()){
            log.info("invalid url");
            response.sendRedirect("/");
            return false;
        }

        /*추출 숫자(memberId) Long 으로 parsing*/
//        log.info("list : {} ", matcher.group());
        Long targetMemberId = Long.parseLong(matcher.group(0));
        /*요청한 member 조회*/
//        log.info("targetMemberId {}", targetMemberId);
        MemberDto targetMember = memberService.findMemberById(targetMemberId);

        /*현재 세션 member 조회*/
        MemberDto accessMember = (MemberDto)session.getAttribute(SessionConst.LOGIN_MEMBER);
        /*요청 member와 세션 member가 다른 경우 -> 이전 화면으로 리다이렉트*/
        if(!accessMember.getId().equals(targetMember.getId())){
            log.info("unAuthorized user access");
            response.sendRedirect(request.getHeader("Referer"));
            return false;
        }
        return true;
    }
}
