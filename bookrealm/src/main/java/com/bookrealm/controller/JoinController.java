package com.bookrealm.controller;

import com.bookrealm.model.dto.JoinDto;
import com.bookrealm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/join")
@RequiredArgsConstructor
@RestController
public class JoinController {

    @Autowired
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody JoinDto dto) {
        userService.join(dto);
        return ResponseEntity.ok().body("회원가입이 성공 했습니다.");
    }
}
