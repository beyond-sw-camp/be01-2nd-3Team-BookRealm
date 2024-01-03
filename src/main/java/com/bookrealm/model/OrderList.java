package com.bookrealm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ORDERLIST")
public class OrderList {

    @Column(length = 10, nullable = false)
    private int buyId;

    @Column(length = 10, nullable = false)
    private int bookId;

    @Column(length = 10, nullable = false)
    private int purchase;

    @Column(length = 10)
    private String status;
}
