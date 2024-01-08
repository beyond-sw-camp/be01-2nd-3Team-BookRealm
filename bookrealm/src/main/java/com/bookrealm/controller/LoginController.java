package com.bookrealm.controller;

import com.bookrealm.model.dto.JoinDto;
import com.bookrealm.model.dto.LoginDto;
import com.bookrealm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RequiredArgsConstructor
@RestController
public class LoginController {

    @Autowired
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> log(@RequestBody LoginDto dto) {
        String token = userService.login(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok().body(token);
    }
}
