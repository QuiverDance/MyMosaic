package com.mymosaic.member.controller;

import com.mymosaic.common.constant.FileDirConst;
import com.mymosaic.common.file.FileManger;
import com.mymosaic.common.file.UploadFile;
import com.mymosaic.member.dto.MemberDto;
import com.mymosaic.member.dto.MemberEditParam;
import com.mymosaic.member.web.MemberInfoEditForm;
import com.mymosaic.member.web.MemberPasswordEditForm;
import com.mymosaic.member.web.RegisterForm;
import com.mymosaic.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    private final MemberService memberService;
    private final FileManger fileManger;

    /*
     * 회원가입 화면 요청
     * */
    @GetMapping("/add")
    public String addForm(@ModelAttribute("form") RegisterForm form, Model model) {
        RegisterForm registerForm = new RegisterForm();
        model.addAttribute("form", registerForm);
        return "members/addMemberForm";
    }

    /*
     * 전달한 회원가입 form을 바탕으로 회원 정보 저장
     * */
    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("form") RegisterForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/addMemberForm";
        }
        MemberDto memberByLoginId = memberService.findMemberByLoginId(form.getLoginId());
        if(memberByLoginId != null){
            result.addError(new FieldError("form", "loginId", "중복 아이디 입니다."));
            return "members/addMemberForm";
        }
        if(!form.getPassword().equals(form.getPassword2())){
            result.addError(new FieldError("form", "password2", "비밀번호가 일치하지 않습니다."));
            return "members/addMemberForm";
        }

        memberService.registerMember(form);
        return "redirect:/";
    }


    /*
     * 회원 정보 화면 요청
     * */
    @GetMapping("/{memberId}")
    public String MemberInfo(@PathVariable("memberId") Long memberId, Model model) throws IOException {
        MemberDto member = memberService.findMemberById(memberId);

        member.setProfileImg(fileManger.loadImage(member.getProfile().getFilePath()));
        log.info(member.getProfile().extractExt());
        model.addAttribute("member", member);
        return "members/myInfo";
    }

    /*
     * 회원 수정 화면 요청
     * */
    @GetMapping("/{memberId}/edit")
    public String editMemberInfo(@PathVariable("memberId") Long memberId, Model model) throws IOException {
        MemberDto member = memberService.findMemberById(memberId);

        MemberInfoEditForm form = new MemberInfoEditForm();
        form.setIntroduction(member.getIntroduction());

        model.addAttribute("id", member.getId());
        model.addAttribute("form", form);
        return "members/editMemberForm";
    }

    /*
     * 전달한 form을 바탕으로 회원 정보 수정
     * */
    @PatchMapping("/{memberId}/edit")
    public String editMemberInfo(@PathVariable("memberId") Long memberId,
                                 @Valid @ModelAttribute("form") MemberInfoEditForm form,
                                 BindingResult result,
                                 Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("id", memberId);
            return "members/editMemberForm";
        }

        UploadFile attachFile = fileManger.storeFile(FileDirConst.MEMBER_PROFILE_DIR, form.getProfileImg());
        //데이터베이스에 저장 (nullable)
        memberService.updateMemberInfo(memberId, new MemberEditParam(form.getIntroduction(), attachFile));
        return "redirect:/members/{memberId}";
    }

    /*
     * 회원 비밀번호 수정 요청
     * */
    @GetMapping("/{memberId}/pwd/edit")
    public String editMemberPassword(@PathVariable("memberId") Long memberId, Model model){
        MemberDto member = memberService.findMemberById(memberId);

        MemberPasswordEditForm form = new MemberPasswordEditForm();

        model.addAttribute("id", member.getId());
        model.addAttribute("form", form);
        return "members/editPasswordForm";
    }

    /*
     * 전달한 form을 바탕으로 회원 비밀번호 수정
     * */
    @PatchMapping("/{memberId}/pwd/edit")
    public String editMemberPassword(@PathVariable("memberId") Long memberId,
                                 @Valid @ModelAttribute("form") MemberPasswordEditForm form,
                                 BindingResult result,
                                 Model model){
        if (result.hasErrors()) {
            model.addAttribute("id", memberId);
            return "members/editPasswordForm";
        }
        if(!form.getPassword().equals(form.getPassword2())){
            model.addAttribute("id", memberId);
            result.addError(new FieldError("form", "password2", "비밀번호가 일치하지 않습니다."));
            return "members/editPasswordForm";
        }
        memberService.updateMemberPassword(memberId, form.getPassword());
        return "redirect:/members/{memberId}";
    }
}
