package com.bookrealm.controller;

import com.bookrealm.model.Book;
import com.bookrealm.model.Member;
import com.bookrealm.model.Order;
import com.bookrealm.model.Role;
import com.bookrealm.naver.NaverBookClient;
import com.bookrealm.naver.dto.SearchBookReq;
import com.bookrealm.naver.dto.SearchBookRes;
import com.bookrealm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final NaverBookClient naverBookClient;
    private final AdminService adminService;

    @Autowired
    AdminController(NaverBookClient naverBookClient, AdminService adminService){

        this.naverBookClient = naverBookClient;
        this.adminService = adminService;
    }

    // 관리자 홈 화면
    @RequestMapping
    public String home(){
        return "/admin/home";
    }

    // DB 저장 도서 관리 페이지
    @RequestMapping("/book/manage")
    public ModelAndView manageBook() {
        ModelAndView mav = new ModelAndView("/admin/book/manage");
        List<Book> bookList = adminService.findAll();
        mav.addObject("bookList",bookList);
        return mav;
    }

    // 수정 폼
    @GetMapping("/book/manage/edit")
    public ModelAndView editBookView(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("/admin/book/edit");
        Optional<Book> book = adminService.findById(id);
        mav.addObject("book", book.get());
        return mav;
    }

    // 수정 요청
    @PostMapping("/book/manage/edit")
    public String editBook(Book editbook){
        adminService.save(editbook);

        return "redirect:/admin/book/manage";
    }

    @GetMapping("/book/search")
    public String searchBook(){

        return "/admin/book/search";
    }
    @GetMapping("/book/search/result")
    public ModelAndView searchBook(@RequestParam(name = "query") String query){

        ModelAndView mav = new ModelAndView("/admin/book/search_result_save");

        SearchBookRes result = naverBookClient.searchBookApi(new SearchBookReq(query));
        //List<SearchBookRes.SearchBookItem> list = result.getItems();

        mav.addObject("result",result);
        return mav;
    }

    /*
        회원 관리 관련
     */
    @GetMapping("/member")
    public ModelAndView manageMembers(){
        ModelAndView mav = new ModelAndView("/admin/user/users");
        List<Member> members = adminService.findAllMember();

        mav.addObject("members", members);
        return mav;
    }

    // 수정 폼
    @GetMapping("/member/manage")
    public ModelAndView editMemberView(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("/admin/user/manage");
        Optional<Member> member = adminService.findMemberById(id);
        List<Order> orders = adminService.findAllOrderByMemberId(id);
        mav.addObject("member", member.get());
        mav.addObject("roles", Role.values());
        mav.addObject("orders", orders);
        return mav;
    }

    // 수정 요청
    @PostMapping("/member/edit")
    public String editMember(Book editbook){
        adminService.save(editbook);

        return "redirect:/admin/member";
    }
}