package com.bookrealm.repository;

import com.bookrealm.model.Order;
import com.bookrealm.model.dto.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);
    List<Order> findByMemberId(Long id);

    @Query("select m,o from Member m left join Order o where m.id = o.member.id")
    List<OrderDto> findAllOrderByMemberId(Long memberId);

    @Query("SELECT o FROM Order o JOIN FETCH o.orderLists ol JOIN FETCH ol.book WHERE o.id = :orderId")
    Order findOrderWithBooksById(@Param("orderId") Long orderId);
}
