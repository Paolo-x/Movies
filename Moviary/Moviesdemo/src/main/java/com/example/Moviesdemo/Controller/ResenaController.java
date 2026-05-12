package com.example.Moviesdemo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.example.Moviesdemo.Model.Resena;
import com.example.Moviesdemo.Service.ResenaService;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping //Listar todas las reseñas
    public List<Resena> listarResenas(){
        return resenaService.listResenas();
    }

    @PostMapping //Agregar reseña
    public Resena agregarResena(@RequestBody Resena resena){
        return resenaService.agregarResena(resena);
    }

    @PutMapping("/{id}") //Actualizar reseña pero solo el COMENTARIO
    public Resena actualizarComentario(@PathVariable Long id, @RequestBody Resena resena){
    return resenaService.actualizarResena(id, resena);
}

@DeleteMapping("/{id}") //ELiminar reseña
    public void eliminarResena(@PathVariable Long id) {
        resenaService.deleteResena(id);
    }

    
}
