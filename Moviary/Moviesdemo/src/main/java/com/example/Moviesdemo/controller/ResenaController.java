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

import com.example.Moviesdemo.model.Resena;
import com.example.Moviesdemo.service.ResenaService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<Resena>> listarResenas() {
        return ResponseEntity.ok(resenaService.listResenas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> obtenerResenaPorId(@PathVariable Long id) { //Busca reseña por su ID
        Resena resena = resenaService.obtenerResenaPorId(id);
        if (resena == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resena);
    }

    @PostMapping
    public ResponseEntity<Resena> agregarResena(@Valid @RequestBody Resena resena) {
        return ResponseEntity.status(HttpStatus.CREATED).body(resenaService.agregarResena(resena));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resena> actualizarComentario(@PathVariable Long id, @Valid @RequestBody Resena resena) {
        Resena actualizada = resenaService.actualizarResena(id, resena);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResena(@PathVariable Long id) {
        resenaService.deleteResena(id);
        return ResponseEntity.noContent().build();
    }

}
