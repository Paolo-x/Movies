package com.example.Moviesdemo.service;

import com.example.Moviesdemo.dto.TmdbPeliculaDTO;
import com.example.Moviesdemo.dto.TmdbResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class TmdbService {

    @Autowired
    @Qualifier("tmdbWebClient")
    private WebClient tmdbWebClient;

    @Value("${tmdb.api-key}")
    private String apiKey;

    public List<TmdbPeliculaDTO> buscarPeliculas(String query) {
        var response = tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("query", query)
                        .queryParam("language", "es-ES")
                        .build())
                .retrieve()
                .bodyToMono(TmdbResponse.class)
                .block();
        return response != null ? response.getResults() : List.of();
    }

    public TmdbPeliculaDTO obtenerPeliculaPorIdTmdb(Long tmdbId) {
        return tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/" + tmdbId)
                        .queryParam("api_key", apiKey)
                        .queryParam("language", "es-ES")
                        .build())
                .retrieve()
                .bodyToMono(TmdbPeliculaDTO.class)
                .block();
    }
}
