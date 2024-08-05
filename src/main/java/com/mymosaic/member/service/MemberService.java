package com.mymosaic.member.service;

import com.mymosaic.member.dto.MemberDto;
import com.mymosaic.member.dto.RegisterForm;
import com.mymosaic.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void registerMember(RegisterForm form){
        memberRepository.save(form.toMember());
    }

    public MemberDto findMemberById(Long id){
        return new MemberDto(memberRepository.findById(id));
    }
}
