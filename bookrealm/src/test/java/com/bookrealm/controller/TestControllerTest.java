package com.bookrealm.controller;

import com.bookrealm.model.Address;
import com.bookrealm.model.SuType;
import com.bookrealm.model.User;
import com.bookrealm.model.dto.JoinDto;
import com.bookrealm.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static com.bookrealm.model.SuType.NORMAL;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class TestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입성공")
    @WithMockUser
    void join() throws Exception {
        String email = "a@naver.com";
        String username = "이영진";
        String passwd = "1234";
        Address address = new Address("서울시", "갈현로4길", "25-9");
        String phone = "01042991435";
        SuType suType = NORMAL;

        mockMvc.perform(post("/test/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new JoinDto(email, username, passwd, address, phone, suType))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test


}