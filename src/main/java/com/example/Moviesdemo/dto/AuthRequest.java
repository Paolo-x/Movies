package com.example.Moviesdemo.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
    private String correo;
}
