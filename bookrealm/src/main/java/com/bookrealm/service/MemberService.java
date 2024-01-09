package com.bookrealm.service;

import com.bookrealm.exception.AppException;
import com.bookrealm.exception.ErrorCode;
import com.bookrealm.model.Member;
import com.bookrealm.model.User;
import com.bookrealm.model.dto.JoinDto;
import com.bookrealm.repository.MemberRepository;
import com.bookrealm.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.bookrealm.model.Role.USER;

@Service

public class MemberService {


    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;



    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder encoder) {
        this.memberRepository = memberRepository;
        this.encoder = encoder;
    }

    public Long join(JoinDto dto) {

        //email 중복 확인
        memberRepository.findByEmail(dto.getEmail())
                .ifPresent(user1 -> {
                    throw new AppException(ErrorCode.EMAIL_DUPLICATED, "이미 존재하는 이메일 입니다.");
                });

        Member member = Member.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .passwd(encoder.encode(dto.getPassword()))
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .suType(dto.getSuType())
                .role(USER)
                .build();

        //저장
        memberRepository.save(member);

        return member.getId();
    }

    public String login(String email, String password) {
        //email 없음
        Member selectedUser = memberRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_FOUND, "이메일이 존재하지 않습니다"));

        //password 틀림
        if(!encoder.matches(password, selectedUser.getPasswd())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드가 잘못 입력 했습니다.");
        }

        return null;
    }
}
