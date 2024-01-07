package com.bookrealm.service;

import com.bookrealm.exception.AppException;
import com.bookrealm.model.Address;
import com.bookrealm.model.SuType;
import com.bookrealm.model.User;
import com.bookrealm.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback(false)
    public void 회원가입() {
        //given
        User user = new User();
        user.setEmail("a@naver.com");
        user.setUsername("이영진");
        user.setPasswd("1234");

        Address address = new Address("서울시", "갈현로4길", "25-9");
        user.setAddress(address);

        user.setPhone("01042991435");
        user.setSuType(SuType.NORMAL);

        //when
        Long saveId = userService.join(user);

        //then
        User findUser = userRepository.findById(saveId).get();
        assertEquals(user.getEmail(), findUser.getEmail());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        User user = new User();
        user.setEmail("a@naver.com");
        user.setUsername("이영진");
        user.setPasswd("1234");

        Address address = new Address("서울시", "갈현로4길", "25-9");
        user.setAddress(address);

        user.setPhone("01042991435");
        user.setSuType(SuType.NORMAL);

        User user2 = new User();
        user2.setEmail("a@naver.com");
        user2.setUsername("a");
        user2.setPasswd("a");
        user2.setAddress(address);

        user2.setPhone("a");
        user2.setSuType(SuType.NORMAL);

        //when
        userService.join(user);
        assertThrows(AppException.class, () -> userService.join(user2));
    }
}