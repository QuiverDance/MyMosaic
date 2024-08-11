package com.mymosaic.member.service;

import com.mymosaic.member.domain.Member;
import com.mymosaic.member.dto.MemberDto;
import com.mymosaic.member.dto.MemberInfoEditForm;
import com.mymosaic.member.dto.RegisterForm;
import com.mymosaic.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public MemberDto findMemberByLoginId(String loginId){
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        return findMember.map(MemberDto::new).orElse(null);
    }

    public void updateMemberInfo(Long id, MemberInfoEditForm form){
        memberRepository.update(id, form);
    }
}
