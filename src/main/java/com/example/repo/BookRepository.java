package com.example.repo;

import com.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
  Optional<Book>findByUsername(String username);
}