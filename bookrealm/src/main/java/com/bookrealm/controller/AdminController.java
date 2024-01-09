package com.bookrealm.controller;

import com.bookrealm.model.Book;
import com.bookrealm.naver.NaverBookClient;
import com.bookrealm.naver.dto.SearchBookReq;
import com.bookrealm.naver.dto.SearchBookRes;
import com.bookrealm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @RequestMapping
    public String home(){
        return "/admin/home";
    }

    @RequestMapping("/book/manage")
    public ModelAndView manageBook() {
        ModelAndView mav = new ModelAndView("/admin/book/manage");
        List<Book> bookList = adminService.findAll();
        mav.addObject("bookList",bookList);
        return mav;
    }

    @GetMapping("/book/search")
    public String searchBook(){

        return "/admin/book/search";
    }
    @GetMapping("/book/search/result")
    public ModelAndView searchBook(@RequestParam(name = "query") String query){

        ModelAndView mav = new ModelAndView("search_result");

        SearchBookRes result = naverBookClient.searchBookApi(new SearchBookReq(query));
        //List<SearchBookRes.SearchBookItem> list = result.getItems();

        mav.addObject("result",result);
        return mav;
    }

    /*@PostMapping("/book/save")
    public String saveBook(@RequestParam(value = "selections") List<String> selections) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<Book> books = new ArrayList<>();

        StringBuilder sb;
        for(String selection : selections){
            HashMap<String, String> map = new HashMap<String, String>();
            System.out.println(selection);
            map = mapper.readValue(selection, new TypeReference<HashMap<String, String>>() {});
            System.out.println(map);
            Book book = new Book();
            book.setTitle(map.get("title"));
            book.setPrice(Integer.parseInt(map.get("discount")));
            book.setImage(map.get("image"));
            book.setWriter(map.get("author"));
            book.setPublisher(map.get("publisher"));
            book.setIsbn(Long.parseLong(map.get("isbn")));
            book.setDescription(map.get("description"));
            sb = new StringBuilder(map.get("pubdate"));
            sb.insert(4,'-').insert(7,'-');
            book.setPublishDate(LocalDate.parse(sb));

            books.add(book);
        }

        adminService.saveAll(books);
        return "redirect:/admin/book/search";

    }*/
}
