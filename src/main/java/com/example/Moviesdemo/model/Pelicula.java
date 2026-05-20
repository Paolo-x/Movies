package com.example.Moviesdemo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pelicula")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotNull
    private Integer anio;

    @NotNull
    private Integer duracion;

    @NotBlank
    private String genero;

    @NotBlank
    @Column(columnDefinition = "TEXT") //Metodo hybernate para convertir en text y convertir un varchar pero a text , es mas largo
    private String sinopsis;

    @NotNull
    private Integer fechaEstreno;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, orphanRemoval = true) // Una pelicula tiene muchas resenas, usa la FK "pelicula_id" de Resena
    @JsonIgnore // Evita loop infinito al serializar JSON (Pelicula -> Resena -> Pelicula -> ...)
    private List<Resena> listaResenas;


}