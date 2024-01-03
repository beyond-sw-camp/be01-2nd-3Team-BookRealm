package com.bookrealm.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String userId;

    private String username;

    private String passwd;

    private String address;

    private String phone;

    private String suType;

    //0 일반회원, 1 관리자
    private int adminyn;

}
