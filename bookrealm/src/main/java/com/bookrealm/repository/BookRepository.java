package com.bookrealm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookrealm.model.Book;
	
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT b FROM Book b")
    Page<Book> findAll(Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:searchTerm% OR b.writer LIKE %:searchTerm%")
    List<Book> findByTitleAndWriter(String searchTerm);

    @Query("SELECT b FROM Book b ORDER BY b.sales DESC")
    Page<Book> findBestSellers(Pageable pageable);
    
    Optional<Book> findById(Long id);

    //void saveAll(List<Book> books);
    
//    페이지 없이 한 페이지에 모든 리스트를 보여줄 때
//    @Query("SELECT b FROM Book b")
//    List<Book> findAll();
//    
//    @Query("SELECT b FROM Book b ORDER BY b.sales DESC")
//    List<Book> findBestSellers();
}
