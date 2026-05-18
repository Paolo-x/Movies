package com.example.Moviesdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbPeliculaDTO {
    

    private Long id;
    private String title;

@JsonProperty("overview")
private String sinopsis;

@JsonProperty("release_date")
private String fechaEstreno;

@JsonProperty("vote_average")
private Double calificacion;

@JsonProperty("poster_path")
private String posterPath;


}
