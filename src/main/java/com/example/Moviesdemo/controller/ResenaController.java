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

import com.example.Moviesdemo.model.Resena;
import com.example.Moviesdemo.service.ResenaService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    private static final Logger log = LoggerFactory.getLogger(ResenaController.class); // Logger SLF4J

    @Autowired
    private ResenaService resenaService;

    @GetMapping // Para obtener la lista de todas las resenas
    public ResponseEntity<List<Resena>> listarResenas() {
        log.info("listarResenas"); // Log al listar todas las resenas
        return ResponseEntity.ok(resenaService.listResenas());
    }

    @GetMapping("/{id}") // Para obtener una resena especifica por su ID
    public ResponseEntity<Resena> obtenerResenaPorId(@PathVariable Long id) { //Busca reseña por su ID
        log.info("obtenerResenaPorId id={}", id); // Log con el id de la resena
        Resena resena = resenaService.obtenerResenaPorId(id);
        if (resena == null) {
            log.warn("Resena no encontrada id={}", id); // log de advertensia si la reseña no existe
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resena);
    }

    @PostMapping // Para crear y guardar una nueva resena
    public ResponseEntity<Resena> agregarResena(@Valid @RequestBody Resena resena) {
        log.info("agregarResena calificacion={}", resena.getCalificacion()); // Log al crear resena
        return ResponseEntity.status(HttpStatus.CREATED).body(resenaService.agregarResena(resena));
    }

    @PutMapping("/{id}") // Para actualizar el comentario o calificacion de una resena por su ID
    public ResponseEntity<Resena> actualizarComentario(@PathVariable Long id, @Valid @RequestBody Resena resena) {
        log.info("actualizarComentario id={}", id); // Log al actualizar resena
        Resena actualizada = resenaService.actualizarResena(id, resena);
        if (actualizada == null) {
            log.warn("Resena no encontrada para actualizar id={}", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}") // Para eliminar una resena de la base de datos por su ID
    public ResponseEntity<Void> eliminarResena(@PathVariable Long id) {
        log.info("eliminarResena id={}", id); // Log al eliminar resena
        resenaService.deleteResena(id);
        return ResponseEntity.noContent().build();
    }

}
