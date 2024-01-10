package com.bookrealm.controller;

import com.bookrealm.model.Book;
import com.bookrealm.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/book/save")
    public ResponseEntity<List<Book>> saveBook(@RequestParam(value = "selections") String selections) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<Book> books = new ArrayList<>();
        List<String> selectList = mapper.readValue(selections, new TypeReference<List<String>>(){});

        StringBuilder sb;
        for (String selection : selectList) {
            Map<String, String> map = mapper.readValue(selection, new TypeReference<Map<String,String>>(){});
            Book book = new Book();
            book.setTitle(map.get("title"));
            book.setPrice(Integer.parseInt(map.get("discount")));
            book.setImage(map.get("image"));
            book.setWriter(map.get("author"));
            book.setPublisher(map.get("publisher"));
            book.setIsbn(Long.parseLong(map.get("isbn")));
            book.setDescription(map.get("description"));
            sb = new StringBuilder(map.get("pubdate"));
            sb.insert(4, '-').insert(7, '-');
            book.setPublishDate(LocalDate.parse(sb));

            books.add(book);
        }
        return new ResponseEntity<List<Book>>(adminService.saveAll(books), HttpStatus.OK);
    }

    @PostMapping("book/delete")
    public ResponseEntity<Void> deleteBooks(@RequestParam(value = "ids[]") List<Long> ids){
        System.out.println(ids);
        adminService.deleteByIdIn(ids);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
