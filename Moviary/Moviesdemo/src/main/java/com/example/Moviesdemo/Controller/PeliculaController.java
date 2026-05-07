package com.example.Moviesdemo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Moviesdemo.Model.Pelicula;
import com.example.Moviesdemo.Service.PeliculaService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping //Listar las peliculas
    public List<Pelicula> listarPeliculas() {
        return peliculaService.listarPeliculas();
    }

    @GetMapping("/{id}") //Obtener las peliculas por el id
    public ResponseEntity<Pelicula> obtenerPorId(@PathVariable Long id) {
        Pelicula pelicula = peliculaService.obtenerPeliculaPorId(id);
        if (pelicula == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pelicula);
    }


        @PostMapping //Agregar pelicula
    public Pelicula agregarPelicula(@RequestBody Pelicula pelicula) {
        return peliculaService.agregarPelicula(pelicula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) {
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
