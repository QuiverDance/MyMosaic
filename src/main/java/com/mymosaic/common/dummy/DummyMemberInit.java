package com.mymosaic.common.dummy;

import com.mymosaic.member.domain.Member;
import com.mymosaic.member.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DummyMemberInit {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init(){
        Member member1 = new Member("test1", "테스터1", "123");
        Member member2 = new Member("test2", "테스터2", "123");
        Member member3 = new Member("test3", "테스터3", "123");

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
    }
}
