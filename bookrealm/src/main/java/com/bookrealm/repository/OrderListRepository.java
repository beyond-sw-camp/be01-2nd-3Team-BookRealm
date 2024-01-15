package com.bookrealm.repository;

import com.bookrealm.model.Book;
import com.bookrealm.model.OrderList;
import com.bookrealm.model.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderListRepository extends JpaRepository<OrderList, Long> {
    List<OrderList> findByOrderId(Long id);

    @Modifying
    @Transactional
    @Query("Update OrderList ol set ol.status=:status where ol.id=:orderListId")
    void updateOrderListStatus(@Param(value = "status") Status status, @Param(value = "orderListId") Long orderListId);
}
