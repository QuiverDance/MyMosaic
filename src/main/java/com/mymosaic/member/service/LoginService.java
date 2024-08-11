package com.mymosaic.member.service;

import com.mymosaic.member.domain.Member;
import com.mymosaic.member.dto.MemberDto;
import com.mymosaic.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public MemberDto login(String loginId, String password){
        /*
        * memberRepository 에서 loginId 에 해당하는 member 가져오기 ->
        * 해당 member의 비밀번호와 전달한 비밀번호가 같은지 확인
        * 비밀번호가 다른 경우 member = null
        * */
        Member member = memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
        return member == null ? null : new MemberDto(member);
    }
}
