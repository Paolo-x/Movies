package com.example.Moviesdemo.Model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 

    @NotBlank
    @Column(unique = true) // No permite usernames repetidos
    private String username;

    @Email
    @NotBlank
    @Column(unique = true) // No permite correos repetidos
    private String correo;

    @NotBlank
    @Size(min = 8) // mínimo 8 caracteres
    private String contrasena;

    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() { 
        this.fechaRegistro = LocalDateTime.now();
    }

    
}