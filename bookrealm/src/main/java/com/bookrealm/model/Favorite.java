package com.bookrealm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long id;  //즐겨찾기 코드 (PK)

    @Column(nullable = false)
    private Integer bookId;  // 도서코드

    @Column(nullable = false)
    private String userId;  // 회원ID
}
