package com.bookrealm.controller;

import com.bookrealm.naver.NaverBookClient;
import com.bookrealm.naver.dto.SearchBookReq;
import com.bookrealm.naver.dto.SearchBookRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final NaverBookClient naverBookClient;

    @Autowired
    AdminController(NaverBookClient naverBookClient){
        this.naverBookClient = naverBookClient;
    }

    @GetMapping("/book/search")
    public String searchBook(){

        return "/admin/book/search";
    }
    @GetMapping("/book/search/result")
    public ModelAndView searchBook(@RequestParam(name = "query") String query){

        ModelAndView mav = new ModelAndView("/admin/book/manage");

        SearchBookRes result = naverBookClient.searchBookApi(new SearchBookReq(query));
        System.out.println(result);
        mav.addObject("result",result);

        return mav;
    }

}
