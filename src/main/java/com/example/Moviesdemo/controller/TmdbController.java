package com.example.Moviesdemo.controller;

import com.example.Moviesdemo.dto.TmdbPeliculaDTO;
import com.example.Moviesdemo.service.TmdbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tmdb")
public class TmdbController {

    private static final Logger log = LoggerFactory.getLogger(TmdbController.class); // Logger SLF4J

    @Autowired
    private TmdbService tmdbService;

    @GetMapping("/buscar") // Para buscar peliculas en la API externa de TMDB usando un texto de consulta
    public ResponseEntity<List<TmdbPeliculaDTO>> buscar(@RequestParam String query) {
        log.info("buscar query={}", query); // Log de busqueda en TMDB
        return ResponseEntity.ok(tmdbService.buscarPeliculas(query));
    }

    @GetMapping("/pelicula/{tmdbId}") // Para obtener los detalles especificos de una pelicula directamente desde TMDB usando su ID externo
    public ResponseEntity<TmdbPeliculaDTO> obtenerPorTmdbId(@PathVariable Long tmdbId) {
        log.info("obtenerPorTmdbId tmdbId={}", tmdbId); // Log al obtener detalle desde TMDB
        TmdbPeliculaDTO pelicula = tmdbService.obtenerPeliculaPorIdTmdb(tmdbId);
        if (pelicula == null) {
            log.warn("Pelicula TMDB no encontrada tmdbId={}", tmdbId); // Log de advertencia si TMDB no devuelve ninguna pelicula
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pelicula);
    }
}
