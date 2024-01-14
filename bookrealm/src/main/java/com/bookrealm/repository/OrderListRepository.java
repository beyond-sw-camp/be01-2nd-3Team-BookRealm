package com.bookrealm.repository;

import com.bookrealm.model.Book;
import com.bookrealm.model.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderListRepository extends JpaRepository<OrderList, Long> {
    List<OrderList> findByOrderId(Long id);
}
