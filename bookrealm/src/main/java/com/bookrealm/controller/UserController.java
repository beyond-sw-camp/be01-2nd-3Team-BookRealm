package com.bookrealm.controller;

import com.bookrealm.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/myPage")
public class UserController {

    private final MemberService memberService;

    @Autowired
    public UserController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String myPage(Model model, Principal principal) {
        model.addAttribute("member", memberService.getUser(principal.getName()));
        return "myPage";
    }

    @PostMapping("/changePassword")
    public String changePassword(Model model, String newPassword) {
        // 비밀번호 변경 로직을 수행하고 결과를 처리하는 코드를 추가해야 합니다.
        // newPassword 파라미터를 이용하여 변경 작업을 수행합니다.
        // 성공 시 적절한 응답을, 실패 시 에러 메시지를 처리합니다.
        return "redirect:/myPage";
    }

    @PostMapping("/changePhoneNumber")
    public String changePhoneNumber(Model model, String newPhoneNumber) {
        // 전화번호 변경 로직을 수행하고 결과를 처리하는 코드를 추가해야 합니다.
        // newPhoneNumber 파라미터를 이용하여 변경 작업을 수행합니다.
        // 성공 시 적절한 응답을, 실패 시 에러 메시지를 처리합니다.
        return "redirect:/myPage";
    }
}
