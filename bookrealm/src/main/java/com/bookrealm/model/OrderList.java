package com.bookrealm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ORDERLISTS")
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private int buyId;

    @Column(nullable = false)
    private int bookId;

    @Column(nullable = false)
    private int purchase;

    private String status;
}
