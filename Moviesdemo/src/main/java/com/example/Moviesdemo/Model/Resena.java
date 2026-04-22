package com.example.Moviesdemo.Model;

import java.time.LocalDate;

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

    private LocalDate fechaResena;

    // Automatizamos la fecha igual que en Usuario
    @PrePersist
    public void prePersist() {
        this.fechaResena = LocalDate.now();
    }

    //1 usuario muchas reseñas


/*    // MUCHAS reseñas pueden pertenecer a UN solo usuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false) 
    private Usuario usuario;

    // MUCHAS reseñas pueden pertenecer a UNA sola película
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pelicula_id", nullable = false)
    private Pelicula pelicula;
 */
}