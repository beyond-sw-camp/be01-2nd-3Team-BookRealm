package com.bookrealm.service;

import com.bookrealm.model.Member;
import com.bookrealm.model.Role;
import com.bookrealm.model.User;
import com.bookrealm.repository.MemberRepository;
import com.bookrealm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
// 스프링 시큐리티가 제공하는 UserDetailsService 인터페이스 구현
public class UserSecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 반드시 오버라이딩 해야 함
    @Override // 사용자명으로 비밀번호 조회
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException{
        Optional<User> _user = this.userRepository.findByEmail(email);

        if(_user.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        User siteUser = _user.get();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if(siteUser.getRole() == Role.ADMIN){
            // 사용자명이 'admin'일 경우에만 ADMIN 권한 부여
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }else{
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }
        return new User(siteUser.getEmail(), siteUser.getPasswd(), authorities);
    }
}
