package com.bookrealm.controller;

import com.bookrealm.model.Book;
import com.bookrealm.model.Role;
import com.bookrealm.model.Status;
import com.bookrealm.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Tag(name = "관리자 API", description = "관리자 페이지 기능 API")
@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/book/save")
    @Operation(summary = "도서 목록 저장", description = "네이버 도서검색 API 검색 결과 중 선택한 도서 정보를 DB에 저장한다.")
    @Parameter(name = "selections", description = "저장될 도서 검색 결과 JSON 포맷")
    public ResponseEntity<List<Book>> saveBook(@RequestParam(value = "selections") String selections) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<Book> books = new ArrayList<>();
        List<String> selectList = mapper.readValue(selections, new TypeReference<List<String>>(){});

        StringBuilder sb;
        for (String selection : selectList) {
            Map<String, String> map = mapper.readValue(selection, new TypeReference<Map<String,String>>(){});
            Book book = new Book();
            book.setTitle(map.get("title"));
            book.setPrice(Integer.parseInt(map.get("discount")));
            book.setImage(map.get("image"));
            book.setWriter(map.get("author"));
            book.setPublisher(map.get("publisher"));
            book.setIsbn(Long.parseLong(map.get("isbn")));
            book.setDescription(map.get("description"));
            sb = new StringBuilder(map.get("pubdate"));
            sb.insert(4, '-').insert(7, '-');
            book.setPublishDate(LocalDate.parse(sb));

            books.add(book);
        }
        return new ResponseEntity<List<Book>>(adminService.saveAll(books), HttpStatus.OK);
    }

    @PostMapping("/book/delete")
    @Operation(summary = "도서 목록 삭제", description = "관리자가 DB에 저장된 도서를 삭제")
    @Parameter(name = "ids[]", description = "삭제할 도서 코드 전체 배열")
    public ResponseEntity<Void> deleteBooks(@RequestParam(value = "ids[]") List<Long> ids){
        adminService.deleteByIdIn(ids);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/member/delete")
    @Operation(summary = "회원 삭제", description = "관리자가 선택한 회원을 삭제")
    @Parameter(name = "id", description = "삭제한 유저 ID")
    public ResponseEntity<Void> deleteUsers(@RequestParam Long id){
        adminService.deleteMemberById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @PostMapping("/member/role")
    @Operation(summary = "관리자 권한 관리", description = "관리자가 일반회원/관리자 권한을 관리")
    @Parameter(name = "role", description = "새로 부여되는 권한")
    @Parameter(name = "id", description = "새로운 권한이 부여되는 유저의 ID")
    public ResponseEntity<Void> editRole(@RequestParam Role role, @RequestParam Long id){
        adminService.updateRole(role,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
