package com.mymosaic.member.service;

import com.mymosaic.common.constant.FileDirConst;
import com.mymosaic.common.file.FileManger;
import com.mymosaic.common.file.UploadFile;
import com.mymosaic.member.domain.Member;
import com.mymosaic.member.dto.MemberDto;
import com.mymosaic.member.dto.MemberEditParam;
import com.mymosaic.member.repository.MemberRepository;
import com.mymosaic.member.web.MemberInfoEditForm;
import com.mymosaic.member.web.RegisterForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final FileManger fileManger;

    public void registerMember(RegisterForm form){
        memberRepository.save(form.toMember());
    }

    public MemberDto findMemberById(Long id) throws IOException {
        MemberDto memberDto = new MemberDto(memberRepository.findById(id));
        memberDto.setProfileImg(fileManger.loadImage(memberDto.getProfile()));
        return memberDto;
    }

    public MemberDto findMemberByLoginId(String loginId){
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        return findMember.map(MemberDto::new).orElse(null);
    }

    public void updateMemberInfo(Long id, MemberInfoEditForm form) throws IOException {
        UploadFile attachFile = fileManger.storeFile(FileDirConst.MEMBER_PROFILE_DIR, form.getProfileImg());
        memberRepository.update(id, form.toParam(attachFile));
    }

    public void updateMemberPassword(Long id, String param){
        memberRepository.updatePassword(id, param);
    }
}
