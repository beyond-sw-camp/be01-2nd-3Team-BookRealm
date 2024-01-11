package com.bookrealm.service;

import com.bookrealm.exception.AppException;
import com.bookrealm.exception.ErrorCode;
import com.bookrealm.model.Address;
import com.bookrealm.model.Member;
import com.bookrealm.model.SuType;
import com.bookrealm.model.dto.JoinDto;
import com.bookrealm.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Address address = new Address(dto.getPostcode(), dto.getAddress(), dto.getDetailAddress(), dto.getExtraAddress());

        Member member = Member.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .passwd(encoder.encode(dto.getPassword1()))
                .address(address)
                .phone(dto.getPhone())
                .suType(SuType.NORMAL)
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

    public Member getUser(String email) {
        Optional<Member> siteUser = this.memberRepository.findByEmail(email);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new AppException(ErrorCode.MEMBER_NOT_FOUND, "siteuser not found");
        }
    }

<<<<<<< HEAD
    public List<Member> findAllUsers(){
        return memberRepository.findAll();
=======
    public Long getUserReturnId(String email) {
        Optional<Member> siteUser = this.memberRepository.findByEmail(email);
        if (siteUser.isPresent()) {
            return siteUser.get().getId();
        } else {
            throw new AppException(ErrorCode.MEMBER_NOT_FOUND, "siteuser not found");
        }
>>>>>>> 35e161041e8ef8705bc2dfa851f5a9e0b96a84eb
    }

}
