package com.example.controller;

import com.example.entity.Book;
import com.example.payload.BookDto;
import com.example.payload.LoginDto;
import com.example.payload.TokenDto;
import com.example.repo.BookRepository;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/bookapp")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping()
    public ResponseEntity<BookDto> addDetails(@RequestBody BookDto bookDto) {
        BookDto saveEntity = bookService.addDetails(bookDto);
        return new ResponseEntity<>(saveEntity, HttpStatus.CREATED);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyLogin(@RequestBody LoginDto loginDto) {
        String token = bookService.verifyLogin(loginDto);
        if (token != null) {

            TokenDto tokenDto = new TokenDto();
            tokenDto.setTokenType("JWT");
            tokenDto.setToken(token);
            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("invalid Username or password", HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/get")
    public ResponseEntity<List<Book>>getAllDetails(
            @RequestParam(name="pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name="pageSize",defaultValue = "3",required = false)int pageSize,
            @RequestParam(name="field",defaultValue = "username",required = false)String field
            ){
        List<Book> books = bookService.getAllDetails(pageNo, pageSize, field);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
}
