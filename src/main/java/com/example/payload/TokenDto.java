package com.example.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {
    private String tokenType;
    private String token;
}
