package com.example.Moviesdemo.controller;

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

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<List<Pelicula>> listarPeliculas() {
        return ResponseEntity.ok(peliculaService.listarPeliculas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPorId(@PathVariable Long id) {
        Pelicula pelicula = peliculaService.obtenerPeliculaPorId(id);
        if (pelicula == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pelicula);
    }

    @PostMapping
    public ResponseEntity<Pelicula> agregarPelicula(@Valid @RequestBody Pelicula pelicula) {
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaService.agregarPelicula(pelicula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable Long id, @Valid @RequestBody Pelicula pelicula) {
        Pelicula actualizada = peliculaService.actualizarPelicula(id, pelicula);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPelicula(@PathVariable Long id) {
        peliculaService.deletePelicula(id);
        return ResponseEntity.noContent().build();
    }

}
