package com.bookrealm.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String postcode;
    private String address;
    private String detailAddress;
    private String extraAddress;

    protected Address() {
    }

    public Address(String postcode, String address, String detailAddress, String extraAddress) {
        this.postcode = postcode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;
    }
}
