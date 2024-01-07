package com.bookrealm.naver;

import com.bookrealm.naver.dto.SearchBookReq;
import com.bookrealm.naver.dto.SearchBookRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NaverBookClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.book}")
    private String naverSearchBookUrl;

    public SearchBookRes searchBookApi(SearchBookReq searchBookReq){
        var uri = UriComponentsBuilder.fromUriString(naverSearchBookUrl)
                .queryParams(searchBookReq.searchBookReqParam())
                .build()
                .encode()
                .toUri();

        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-NAVER-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);     // 전송 타입 지정 - JSON

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchBookRes>(){};

        var responseEntity = new RestTemplate().exchange(
                uri, HttpMethod.GET, httpEntity, responseType
        );

        return responseEntity.getBody();
    }
}
