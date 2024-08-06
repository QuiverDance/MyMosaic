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

        member1.updateProfileImgUrl("/members/profile/테스터1.png");
        member2.updateProfileImgUrl("/members/profile/테스터2.png");
        member3.updateProfileImgUrl("/members/profile/테스터3.png");
        member1.updateIntroduction("테스터1 입니다.");
        member2.updateIntroduction("테스터2 입니다.");
        member3.updateIntroduction("테스터3 입니다.");

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
    }
}
