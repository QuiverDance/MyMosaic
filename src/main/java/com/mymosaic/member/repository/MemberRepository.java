package com.mymosaic.member.repository;

import com.mymosaic.member.domain.Member;
import com.mymosaic.member.dto.MemberEditParam;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    void save(Member member);
    void update(Long id, MemberEditParam param);
    void updatePassword(Long id, String param);
    Member findById(Long id);
    List<Member> findAll();
    Optional<Member> findByLoginId(String loginId);
}
