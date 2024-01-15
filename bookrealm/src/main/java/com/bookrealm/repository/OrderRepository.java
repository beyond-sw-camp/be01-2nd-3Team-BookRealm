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

    //@Query("select m,o from Member m left join Order o on m.id = o.member.id")
    @Query("SELECT o from Order o where o.member.id = :memberId")
    List<Order> findAllOrderByMemberId(@Param(value = "memberId") Long memberId);

}
