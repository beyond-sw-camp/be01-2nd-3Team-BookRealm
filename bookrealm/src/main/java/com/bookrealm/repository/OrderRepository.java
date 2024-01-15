package com.bookrealm.repository;

import com.bookrealm.model.Order;
import com.bookrealm.model.dto.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);

    @Query("select m,o from Member m left join Order o where m.id = o.member.id")
    List<OrderDto> findAllOrderByMemberId(Long memberId);
}
