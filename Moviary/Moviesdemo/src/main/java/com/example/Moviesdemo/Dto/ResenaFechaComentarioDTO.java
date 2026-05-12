package com.example.Moviesdemo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResenaFechaComentarioDTO {

    private String comentario;
    private Integer fechaResena;
}
