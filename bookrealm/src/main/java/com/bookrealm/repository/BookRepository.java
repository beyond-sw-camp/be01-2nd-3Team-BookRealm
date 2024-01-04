package com.bookrealm.repository;

import com.bookrealm.model.book;
@Repository
public interface bookRepository extends JpaRepository<book, Integer> {

    Optional<book> findByISBN(Integer ISBN);

}
