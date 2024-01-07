package com.bookrealm.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchBookReq {

    private String query = "";
    private Integer display = 10;  // 한 페이지에 표시할 검색 결과 수(최대 100)
    private Integer start = 1;     // 검색 시작 위치(최대 100)
    private String sort ="sim";     // 검색결과 정렬(sim : 정확도 순 내림차순 / date : 출간일 순 내림차순)

    public MultiValueMap<String, String> searchBookReqParam() {
        var map = new LinkedMultiValueMap<String, String>();

        map.add("query",query);
        map.add("display",String.valueOf(display));
        map.add("start",String.valueOf(start));
        map.add("sort",sort);

        return map;
    }
}
