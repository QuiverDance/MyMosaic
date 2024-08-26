package com.mymosaic.common.interceptor;

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
public class OwnerCheckInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("owner check interceptor start {}", requestURI);
        /*현재 세션 가져오기*/
        HttpSession session = request.getSession();


        /*요청 uri 에서 숫자 추출({memberId} 부분)*/
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(requestURI);
        /*요청 uri 에서 숫자가 없는 경우 -> 홈 화면으로 리다이렉트*/
        if(!matcher.find()){
            log.info("invalid url");
            response.sendRedirect("/");
            return false;
        }

        /*추출 숫자(memberId, url의 첫 번째 숫자) Long 으로 parsing*/
        Long targetMemberId = Long.parseLong(matcher.group(0));
        /*요청한 member 조회*/
        MemberDto targetMember = memberService.findMemberById(targetMemberId);

        /*현재 세션 member 조회*/
        if(session != null && session.getAttribute(SessionConst.LOGIN_MEMBER) != null){
            MemberDto accessMember = (MemberDto)session.getAttribute(SessionConst.LOGIN_MEMBER);
            /*요청 member와 세션 member가 같은 경우*/
            if(accessMember.getId().equals(targetMember.getId())){
                log.info("owner access");
                request.setAttribute("isOwner", true);
                return true;
            }
        }

        log.info("public access");
        request.setAttribute("isOwner", false);
        return true;
    }
}
