package com.bookrealm.service;

import com.bookrealm.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {
    List<Book> saveAll(List<Book> books);
}
