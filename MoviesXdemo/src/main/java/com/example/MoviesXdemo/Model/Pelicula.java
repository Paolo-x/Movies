package com.example.MoviesXdemo.Model;

import java.time.LocalDate;

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
    private Integer id;

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
    private LocalDate fechaEstreno;
}