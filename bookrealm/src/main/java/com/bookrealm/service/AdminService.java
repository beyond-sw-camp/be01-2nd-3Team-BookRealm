package com.bookrealm.service;

import com.bookrealm.model.Book;
import com.bookrealm.model.Member;
import com.bookrealm.model.Role;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    List<Book> saveAll(List<Book> books);
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Book save(Book book);

    void deleteByIdIn(List<Long> ids);

    List<Member> findAllMember();

    Optional<Member> findMemberById(Long id);

    void saveMember(Member member);

    void deleteMemberById(Long id);

    void updateRole(Role role, Long id);
}
