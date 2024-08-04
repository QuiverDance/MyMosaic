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
        Optional<Member> member = memberRepository.findByLoginId(loginId);
        if(member.isPresent() && member.get().getPassword().equals(password)){
            return new MemberDto(member.get());
        }
        else return null; */

        Member member = memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
        return member == null ? null : new MemberDto(member);
    }
}
