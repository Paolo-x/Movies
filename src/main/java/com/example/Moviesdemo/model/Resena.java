package com.example.Moviesdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resena")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value =10, message = "La calificación máxima es 10")
    private Integer calificacion;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String comentario;

   @NotNull
   private Integer fechaResena;

   @ManyToOne // Muchas resenas pertenecen a un solo usuario
    @JoinColumn(name = "usuario_id", nullable = false) // FK hacia la tabla usuario
    @JsonIgnore // Evita serializar el usuario completo dentro de la resena
    private Usuario usuario;

    @ManyToOne // Muchas resenas pertenecen a una sola pelicula
    @JoinColumn(name = "pelicula_id", nullable = false) // FK hacia la tabla pelicula
    @JsonIgnore // Evita serializar la pelicula completa dentro de la resena
    private Pelicula pelicula;



}