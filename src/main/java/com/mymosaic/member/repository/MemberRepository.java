package com.mymosaic.member.repository;

import com.mymosaic.member.domain.Member;
import com.mymosaic.member.dto.MemberInfoForm;
import com.mymosaic.member.dto.RegisterForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    public void save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        log.info("save member : {}", member);
    }

    public Member update(Long id, MemberInfoForm form){
        Member member = findById(id);
        if(!form.getProfileUrl().isEmpty())
            member.updateProfileImgUrl(form.getProfileUrl());
        if(!form.getIntroduction().isEmpty())
            member.updateIntroduction(form.getIntroduction());

        store.put(member.getId(), member);
        log.info("update member : {}", member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public Optional<Member> findByLoginId(String loginId){
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public void clearStore(){
        store.clear();
    }
}
