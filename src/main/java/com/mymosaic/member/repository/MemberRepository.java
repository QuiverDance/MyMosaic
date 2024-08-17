package com.mymosaic.member.repository;

import com.mymosaic.member.domain.Member;
import com.mymosaic.member.dto.MemberEditParam;
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

    /*
    * 전달한 Member 객체를 저장소에 저장
    * */
    public void save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        log.info("save member : {}", member);
    }

    /*
    * id에 해당하는 Member 정보 수정
    * */
    public void update(Long id, MemberEditParam param){
        Member member = findById(id);
        
        /*프로필 이미지와 소개글 수정*/
        if(param.getUploadFile() != null)
            member.updateProfile(param.getUploadFile());
        if(param.getIntroduction() != null)
            member.updateIntroduction(param.getIntroduction());

        log.info("update member : {}", member);
    }

    /*
    * id에 해당하는 Member 비밀번호 수정
    * */
    public void updatePassword(Long id, String param){
        Member member = findById(id);
        member.updatePassword(param);
    }

    /*
     * id에 해당하는 Member 조회
     * */
    public Member findById(Long id){
        return store.get(id);
    }
    
    /*
     * 모든 Member 조회
     * */
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    /*
     * loginId에 해당하는 Member 조회
     * */
    public Optional<Member> findByLoginId(String loginId){
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    /*
    * 저장소 비우기
    * */
    public void clearStore(){
        store.clear();
    }
}
