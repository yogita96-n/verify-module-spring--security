package com.example.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private long id;
    private String bookName;
    private String username;

    private String password;
    private int price;
}
