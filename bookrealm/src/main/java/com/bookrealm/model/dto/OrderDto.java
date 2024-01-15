package com.bookrealm.model.dto;

import com.bookrealm.model.Address;
import com.bookrealm.model.Member;
import com.bookrealm.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderDto {
    private Payment payment;
    private Address destination;
    private Member member;
}
