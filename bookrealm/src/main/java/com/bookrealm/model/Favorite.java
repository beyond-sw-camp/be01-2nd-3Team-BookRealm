package com.bookrealm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer favorId;  //즐겨찾기 코드 (PK)

    @Column(length = 10)
    private Integer bookId;  // 도서코드

    @Column(length = 10)
    private String userId;  // 회원ID
}
