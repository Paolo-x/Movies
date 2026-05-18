package com.example.Moviesdemo.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbResponse {
    
    @JsonProperty("results")
    private List<TmdbPeliculaDTO> results;
}
