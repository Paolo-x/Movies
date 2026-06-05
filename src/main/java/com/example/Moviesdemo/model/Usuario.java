package com.example.Moviesdemo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    private Integer fechaRegistro;

    @Column(nullable = false)
    private String role = "ROLE_USER";

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true) // Si se borra el usuario, se borran sus reseñas
    @ToString.Exclude // Evita que el toString() cargue reseñas y cause problemas de rendimiento o loops
    @EqualsAndHashCode.Exclude // Evita que se incluyan reseñas en equals/hashCode
    private List<Resena> resenas = new ArrayList<>();
}