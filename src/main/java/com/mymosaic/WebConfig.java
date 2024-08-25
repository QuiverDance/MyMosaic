package com.mymosaic;

import com.mymosaic.common.interceptor.LoginCheckInterceptor;
import com.mymosaic.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final MemberService memberService;

    /*
    * 인터셉터 등록
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor(memberService))
                .order(1)
                .addPathPatterns("/**/edit", "/**/add", "/**/delete")
                .excludePathPatterns("/members/add");
    }
}
