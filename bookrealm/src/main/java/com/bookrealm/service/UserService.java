package com.bookrealm.service;

import com.bookrealm.exception.AppException;
import com.bookrealm.exception.ErrorCode;
import com.bookrealm.model.User;
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

    public Long join(User user) {

        //email 중복 확인
        userRepository.findByEmail(user.getEmail())
                .ifPresent(user1 -> {
                    throw new AppException(ErrorCode.EMAIL_DUPLICATED, "이미 존재하는 이메일 입니다.");
                });

        user.setPasswd(encoder.encode(user.getPasswd()));
        user.setAdminyn(USER);
        //저장
        userRepository.save(user);

        return user.getId();
    }
}
