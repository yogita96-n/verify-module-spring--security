package com.example.service;

import com.example.entity.Book;
import com.example.payload.BookDto;
import com.example.payload.LoginDto;
import com.example.payload.TokenDto;
import com.example.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
@Autowired private BookRepository bookRepository;
@Autowired
private JWTService jwtService;
    public BookDto addDetails(BookDto bookDto) {
        Book book = mapToEntity(bookDto);
        PasswordEncoder pass=new BCryptPasswordEncoder();
        book.setPassword(pass.encode(book.getPassword()));
        Book saveEntity = bookRepository.save(book);
        BookDto bookDto1 = mapToDto(saveEntity);
        return bookDto1;
    }
    Book mapToEntity(BookDto bookDto){
        Book book= new Book();
        book.setId(bookDto.getId());
        book.setBookName(bookDto.getBookName());
        book.setPrice(bookDto.getPrice());
        book.setUsername(bookDto.getUsername());
        book.setPassword(bookDto.getPassword());
        return book;
    }
    BookDto mapToDto(Book book){
        BookDto bookDto= new BookDto();
        bookDto.setId(book.getId());
        bookDto.setBookName(book.getBookName());
        bookDto.setPrice(book.getPrice());
        bookDto.setUsername(book.getUsername());
        bookDto.setPassword(book.getPassword());
        return bookDto;
    }

    public String  verifyLogin(LoginDto loginDto) {
        Optional<Book> opUsername = bookRepository.findByUsername(loginDto.getUsername());
        Book book = opUsername.get();
        if(BCrypt.checkpw(loginDto.getPassword(),book.getPassword())){
            return jwtService.generateToken(book);
        }
        return null;
    }

    public List<Book> getAllDetails(int pageNo, int pageSize, String field) {
        Page<Book> all = bookRepository.findAll(PageRequest.of(pageNo, pageSize).withSort(Sort.by(field)));
        List<Book> content = all.getContent();
        return content;

    }
}
