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

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
public class DataAccessInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    /*
    * 접근을 요청한 사용자가 해당 데이터에 접근할 권한이 있는지 확인(본인 소유만 접근 가능)
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*로그 확인용 요청 uri 저장*/
        String requestURI = request.getRequestURI();
        log.info("data access interceptor start {}", requestURI);
        /*현재 세션 가져오기*/
        HttpSession session = request.getSession();

        if (isLogin(response, session, requestURI)) return false;

        /*요청 uri 에서 숫자 추출({memberId} 부분)*/
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(requestURI);
        if (havaMemberId(response, matcher)) return false;

        /*추출 숫자(memberId, url의 첫 번째 숫자) Long 으로 parsing*/
        Long targetMemberId = Long.parseLong(matcher.group(0));
        MemberDto targetMember = memberService.findMemberById(targetMemberId);
        MemberDto accessMember = (MemberDto)session.getAttribute(SessionConst.LOGIN_MEMBER);

        /*요청 member와 세션 member가 다른 경우 -> 홈 화면으로 리다이렉트*/
        if(!accessMember.getId().equals(targetMember.getId())){
            log.info("unauthorized user access");
            response.sendRedirect("/");
            return false;
        }

        // 쿼리 파라미터가 있는지 확인
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (!parameterMap.isEmpty()) {
            // 쿼리 파라미터를 request의 attribute로 설정
            parameterMap.forEach((key, values) -> {
                if(values.length == 1){
                    request.setAttribute(key, values[0]);
                }
                else{
                    request.setAttribute(key, values);
                }
            });
        }

        return true;
    }

    private boolean havaMemberId(HttpServletResponse response, Matcher matcher) throws IOException {
        /*요청 uri 에서 숫자가 없는 경우 -> 홈 화면으로 리다이렉트*/
        if(!matcher.find()){
            log.info("invalid url");
            response.sendRedirect("/");
            return true;
        }
        return false;
    }

    private boolean isLogin(HttpServletResponse response, HttpSession session, String requestURI) throws IOException {
        /*세션 또는 LOGIN_MEMBER 세션이 없는 경우 -> 로그인 화면으로 리다이렉트*/
        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
            log.info("Unauthenticated user access");
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return true;
        }
        return false;
    }
}
