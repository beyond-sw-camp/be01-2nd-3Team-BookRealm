package com.bookrealm.controller;

import com.bookrealm.exception.AppException;
import com.bookrealm.exception.ErrorCode;
import com.bookrealm.model.dto.LoginDto;
import com.bookrealm.repository.UserRepository;
import com.bookrealm.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;
    @Test
    @WithMockUser
    void 로그인_성공() throws Exception {
        String email = "yjin@naver.com";
        String password = "1234";

        when(userService.login(any(), any()))
                .thenReturn("token");

        mockMvc.perform(post("/login/login")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new LoginDto(email, password))))
        .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    void 로그인_실패_이메일없음() throws Exception {
        String email = "yjin@naver.com";
        String password = "1234";

        when(userService.login(any(), any()))
                .thenThrow(new AppException(ErrorCode.EMAIL_NOT_FOUND, " "));

        mockMvc.perform(post("/login/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new LoginDto(email, password))))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser
    void 로그인_실패_비밀번호_틀림() throws Exception {
        String email = "yjin@naver.com";
        String password = "1234";

        when(userService.login(any(), any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD, " "));

        mockMvc.perform(post("/login/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new LoginDto(email, password))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}