package com.bookrealm.controller;

import com.bookrealm.model.Address;
import com.bookrealm.model.Member;
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

    @GetMapping("/address")
    public String addressMod(Model model, Principal principal) {
        model.addAttribute("address", memberService.getUser(principal.getName()).getAddress());
        return "addressMod";
    }

    @PostMapping("/changeAddress")
    public String changeAddress(Model model, Address address, Principal principal) {
        memberService.addressMod(principal.getName(), address);
        return "redirect:/myPage";
    }

    @GetMapping("/phone")
    public String phoneMod(Model model, Principal principal) {
        return "phoneMod";
    }

    @PostMapping("/changePhone")
    public String changePhone(Model model, String phoneNumber, Principal principal) {
        memberService.phoneMod(principal.getName(), phoneNumber);
        return "redirect:/myPage";
    }
    @PostMapping("/changePassword")
    public String changePassword(Model model,String currentPassword, String newPassword, Principal principal) {

        memberService.passwordMod(principal.getName(), currentPassword, newPassword);
        return "redirect:/myPage";
    }

}
