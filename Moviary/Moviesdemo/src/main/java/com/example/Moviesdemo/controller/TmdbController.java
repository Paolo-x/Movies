package com.example.Moviesdemo.controller;

import com.example.Moviesdemo.dto.TmdbPeliculaDTO;
import com.example.Moviesdemo.service.TmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tmdb")
public class TmdbController {

    @Autowired
    private TmdbService tmdbService;

    @GetMapping("/buscar")
    public ResponseEntity<List<TmdbPeliculaDTO>> buscar(@RequestParam String query) {
        return ResponseEntity.ok(tmdbService.buscarPeliculas(query));
    }

    @GetMapping("/pelicula/{tmdbId}")
    public ResponseEntity<TmdbPeliculaDTO> obtenerPorTmdbId(@PathVariable Long tmdbId) {
        TmdbPeliculaDTO pelicula = tmdbService.obtenerPeliculaPorIdTmdb(tmdbId);
        if (pelicula == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pelicula);
    }
}
