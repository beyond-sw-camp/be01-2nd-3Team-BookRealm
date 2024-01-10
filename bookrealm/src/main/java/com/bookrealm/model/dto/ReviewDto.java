package com.bookrealm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ReviewDto { //뷰에만 보이는 값들

    private String contents;
    private int popular;

}
