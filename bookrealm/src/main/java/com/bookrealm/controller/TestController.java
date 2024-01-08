package com.bookrealm.controller;

import com.bookrealm.naver.NaverBookClient;
import com.bookrealm.naver.dto.SearchBookReq;
import com.bookrealm.naver.dto.SearchBookRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/test")
@Controller
public class TestController {

    @Autowired
    private NaverBookClient naverBookClient;

    @PostMapping("/admin/book/search")
    public ModelAndView searchBook(@RequestParam(value = "query") String query){

        ModelAndView mav = new ModelAndView("/admin/book/result");

        SearchBookRes result = naverBookClient.searchBookApi(new SearchBookReq(query));

        mav.addObject("result",result);

        return mav;
    }
}
