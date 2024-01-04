package com.bookrealm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;  // 장바구니 코드 (PK)

    @Column(length = 20, nullable = false)
    private Integer purchase;  //수량

    @Column(length = 20, nullable = false)
    private Integer bookId; // 도서코드

    @Column(length = 20, nullable = false)
    private String userId;


}
