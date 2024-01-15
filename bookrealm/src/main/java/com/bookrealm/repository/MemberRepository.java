package com.bookrealm.repository;

import com.bookrealm.model.Member;
import com.bookrealm.model.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Transactional
    @Modifying      // 쿼리를 실행 후 자동으로 영속성 컨텍스트를 클리어
    @Query(value = "UPDATE Member m SET m.role = :role WHERE m.id = :id")
    void updateMemberRoleById(@Param(value = "role") Role role, @Param(value = "id") Long id);
}
