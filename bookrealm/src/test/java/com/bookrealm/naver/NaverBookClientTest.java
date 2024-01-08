package com.bookrealm.naver;

import com.bookrealm.naver.dto.SearchBookReq;
import com.bookrealm.naver.dto.SearchBookRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NaverBookClientTest {

    @Autowired
    private NaverBookClient naverBookClient;

    @Test
    public void searchBookTest(){
        var search = new SearchBookReq();
        search.setQuery("난");

        SearchBookRes result = naverBookClient.searchBookApi(search);

        System.out.println(result);
    }
}