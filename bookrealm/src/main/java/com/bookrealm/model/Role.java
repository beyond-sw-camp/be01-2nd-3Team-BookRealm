package com.bookrealm.model;

import lombok.Getter;

// 상수 자료형이므로 @Setter는 사용 x
@Getter
// 열거 자료형(enum)
public enum Role {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    Role(String value){
        this.value = value;
    }

    private String value;
}