package com.mymosaic.member.repository;

import com.mymosaic.member.domain.Member;
import com.mymosaic.member.dto.MemberInfoEditForm;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    private final MemberRepository memberRepository = new MemberRepository();

    @Test
    void save() {
    }

    @Test
    void update() {
        Member member1 = new Member("test1", "tester1", "123");
        Member member2 = new Member("test2", "tester2", "123");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Long id = member1.getId();
        MemberInfoEditForm updateParam = new MemberInfoEditForm();
        updateParam.setProfileUrl("/members/profile/tester1.png");
        updateParam.setIntroduction("Hello world");

        memberRepository.update(id, updateParam);

        Member findMember = memberRepository.findById(id);

        assertThat(findMember.getProfileUrl()).isEqualTo(updateParam.getProfileUrl());
        assertThat(findMember.getIntroduction()).isEqualTo(updateParam.getIntroduction());
    }
}