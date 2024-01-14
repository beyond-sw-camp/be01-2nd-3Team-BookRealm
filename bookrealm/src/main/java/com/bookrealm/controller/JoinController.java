package com.bookrealm.controller;

import com.bookrealm.model.dto.JoinDto;
import com.bookrealm.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/join")
@RequiredArgsConstructor
@Controller
public class JoinController {

    @Autowired
    private final MemberService memberService;

    @GetMapping
    public String join(@ModelAttribute("JoinDto") JoinDto joinDto){
        return "signup_form";
    }

    @PostMapping
    public String signup(@ModelAttribute("JoinDto") @Valid JoinDto joinDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "signup_form";
        }

        if(!joinDto.getPassword1().equals(joinDto.getPassword2())){
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        memberService.join(joinDto);

        return "redirect:/";
    }
}
