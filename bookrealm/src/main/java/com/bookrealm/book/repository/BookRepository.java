package com.bookrealm.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookrealm.book.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByBookId(Book bookId);

}
