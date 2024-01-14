package com.bookrealm.service;

import com.bookrealm.model.Book;
import com.bookrealm.model.Member;
import com.bookrealm.model.Role;
import com.bookrealm.repository.BookRepository;
import com.bookrealm.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public AdminServiceImpl(BookRepository bookRepository, MemberRepository memberRepository) {

        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }


    @Override
    public List<Book> saveAll(List<Book> books) {
        return bookRepository.saveAll(books);
        //return 0;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        bookRepository.deleteAllById(ids);
    }

    @Override
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> findMemberById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public void updateRole(Role role, Long id) {
        memberRepository.updateMemberRoleById(role,id);
    }


}
