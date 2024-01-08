package com.bookrealm.model.dto;

import com.bookrealm.model.Address;
import com.bookrealm.model.SuType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JoinDto {
    private String email;
    private String username;
    private String passwd;
    private Address address;
    private String phone;
    private SuType suType;
}
