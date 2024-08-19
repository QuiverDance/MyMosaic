package com.mymosaic.common.dummy;

import com.mymosaic.member.domain.Member;
import com.mymosaic.member.repository.MemberRepository;
import com.mymosaic.member.repository.MemoryMemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DummyMemberInit {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init(){
        Member member1 = new Member("test1@aaa.com", "테스터1", "qwer123!");
        Member member2 = new Member("test2@aaa.com", "테스터2", "qwer123!");
        Member member3 = new Member("test3@aaa.com", "테스터3", "qwer123!");

        member1.updateIntroduction("테스터1 입니다.");
        member2.updateIntroduction("테스터2 입니다.");
        member3.updateIntroduction("테스터3 입니다.");

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
    }
}
