package com.example.Moviesdemo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Moviesdemo.Model.Resena;
import com.example.Moviesdemo.Repository.ResenaRepository;

import java.util.List;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;
    
    public Resena agregarResena(Resena resena){ //Agregar reseña

        return resenaRepository.save(resena);
    }

    public List<Resena> listResenas(){ //listar las reseñas por el usuairo
          
        return resenaRepository.findAll();
    }

    //public List<Resena> listarResenasPorUsuario(Long usuarioId) listar todas las reseñas de un usuario

    public void deleteResena(Long id){ //Eliminar reseñas por id
        resenaRepository.deleteById(id);
    }

    public Resena obtenerResenaPorId(Long id) { //Obtener reseña por id
        return resenaRepository.findById(id).orElse(null);
    }

    public Resena actualizarResena(Long id, Resena resenaActualizada) { //Actualizar solo por comentario y calificacion
        Resena resena = resenaRepository.findById(id).orElse(null);
        if (resena == null) return null;

        resena.setComentario(resenaActualizada.getComentario());
        resena.setCalificacion(resenaActualizada.getCalificacion());

        return resenaRepository.save(resena);
    }
    
}
