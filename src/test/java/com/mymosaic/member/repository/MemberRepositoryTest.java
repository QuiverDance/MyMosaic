package com.mymosaic.member.repository;

import com.mymosaic.common.file.UploadFile;
import com.mymosaic.member.domain.Member;
import com.mymosaic.member.dto.MemberEditParam;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    private final MemberRepository memberRepository = new MemberRepository();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member1 = new Member("test1", "tester1", "123");
        Member member2 = new Member("test2", "tester2", "123");

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> findAll = memberRepository.findAll();

        //then
        assertThat(findAll.size()).isEqualTo(2);
    }

    @Test
    void update() {
        //given
        Member member1 = new Member("test1", "tester1", "123");
        Member member2 = new Member("test2", "tester2", "123");
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        Long id = member1.getId();
        UploadFile uploadFile = new UploadFile("test.png", "test.png", "/testPath/test.png");
        MemberEditParam param = new MemberEditParam("Hello world", uploadFile);

        memberRepository.update(id, param);

        Member findMember = memberRepository.findById(id);

        //then
        assertThat(findMember.getProfile().getFilePath()).isEqualTo(uploadFile.getFilePath());
        assertThat(findMember.getIntroduction()).isEqualTo(param.getIntroduction());
    }
}