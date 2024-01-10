package com.bookrealm.model.dto;

import com.bookrealm.model.Address;
import com.bookrealm.model.SuType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JoinDto {
    @NotEmpty(message = "이메일는 필수항목입니다.")
    private String email;
    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;
    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;
    private String username;
    private String postcode;
    private String address;
    private String detailAddress;
    private String extraAddress;
    private String phone;
}
