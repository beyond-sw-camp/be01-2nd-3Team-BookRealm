package com.bookrealm.service;

import com.bookrealm.model.Book;
import com.bookrealm.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    private final BookRepository bookRepository;

    @Autowired
    public AdminServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /* @Autowired
     public AdminServiceImpl(BookRepository bookRepository){
         this.bookRepository = bookRepository;
     }
 */
    @Override
    public List<Book> saveAll(List<Book> books) {
        return bookRepository.saveAll(books);
        //return 0;
    }
}
