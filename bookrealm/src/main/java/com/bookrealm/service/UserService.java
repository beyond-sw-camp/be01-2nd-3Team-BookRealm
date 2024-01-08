package com.bookrealm.service;

import com.bookrealm.exception.AppException;
import com.bookrealm.exception.ErrorCode;
import com.bookrealm.model.User;
import com.bookrealm.model.dto.JoinDto;
import com.bookrealm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.bookrealm.model.Admin.USER;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public Long join(JoinDto dto) {

        //email 중복 확인
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(user1 -> {
                    throw new AppException(ErrorCode.EMAIL_DUPLICATED, "이미 존재하는 이메일 입니다.");
                });

        User user = User.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .passwd(encoder.encode(dto.getPasswd()))
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .suType(dto.getSuType())
                .adminyn(USER)
                .build();

        //저장
        userRepository.save(user);

        return user.getId();
    }

    public String login(String email, String password) {
        //email 없음
        User selectedUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_FOUND, "이메일이 존재하지 않습니다"));

        //password 틀림
        return "";
    }
}
