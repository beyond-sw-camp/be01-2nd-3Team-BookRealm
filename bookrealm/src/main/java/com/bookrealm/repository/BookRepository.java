package com.bookrealm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Modifying
    @Query("delete from Book b where b.id in :ids")
    void deleteAllByIds(@Param("ids") List<Long> ids);

}
