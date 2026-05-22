package com.example.Moviesdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Moviesdemo.model.Pelicula;
import com.example.Moviesdemo.service.PeliculaService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/peliculas")
public class PeliculaController {

    private static final Logger log = LoggerFactory.getLogger(PeliculaController.class); // Logger SLF4J

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping // para obtener la lista de todas las peliculas
    public ResponseEntity<List<Pelicula>> listarPeliculas() {
        log.info("listarPeliculas"); // Log de info al listar peliculas
        return ResponseEntity.ok(peliculaService.listarPeliculas());
    }

    @GetMapping("/{id}") // Para obtener una pelicula especifica por su ID
    public ResponseEntity<Pelicula> obtenerPorId(@PathVariable Long id) {
        log.info("obtenerPorId id={}", id); // Log con el id recibido
        Pelicula pelicula = peliculaService.obtenerPeliculaPorId(id);
        if (pelicula == null) {
            log.warn("Pelicula no encontrada id={}", id); // Log de advertencia si no existe
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pelicula);
    }

    @PostMapping // Para crear y guardar una nueva pelicula
    public ResponseEntity<Pelicula> agregarPelicula(@Valid @RequestBody Pelicula pelicula) {
        log.info("agregarPelicula titulo={}", pelicula.getTitulo()); // Log del titulo de la pelicula creada
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaService.agregarPelicula(pelicula));
    }

    @PutMapping("/{id}") // Para actualizar los datos de una pelicula existente por su ID
    public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable Long id, @Valid @RequestBody Pelicula pelicula) {
        log.info("actualizarPelicula id={}", id); // Log al actualizar pelicula
        Pelicula actualizada = peliculaService.actualizarPelicula(id, pelicula);
        if (actualizada == null) {
            log.warn("Pelicula no encontrada para actualizar id={}", id); // Log de advertencia si no existe para actualizar
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}") // Para eliminar una pelicula de la base de datos por su ID
    public ResponseEntity<Void> eliminarPelicula(@PathVariable Long id) {
        log.info("eliminarPelicula id={}", id); // Log al eliminar pelicula
        peliculaService.deletePelicula(id);
        return ResponseEntity.noContent().build();
    }

}
