package com.example.Moviesdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Moviesdemo.model.Pelicula;
import com.example.Moviesdemo.repository.PeliculaRepository;

import java.util.List;

@Service
public class PeliculaService {
    
    @Autowired
    private PeliculaRepository peliculaRepository;


    public Pelicula agregarPelicula(Pelicula pelicula) { //Agregar pelicula
        return peliculaRepository.save(pelicula);
    }


    public List<Pelicula> listarPeliculas() { //Listar peliculas
        return peliculaRepository.findAll();
    }


    public void deletePelicula(Long id){ //Eliminar pelicula por id
        peliculaRepository.deleteById(id);
    }
    

    public Pelicula obtenerPeliculaPorId(Long id) { //Obtene pelicula por id
        return peliculaRepository.findById(id).orElse(null);
    }


        public Pelicula actualizarPelicula(Long id, Pelicula peliculaActualizada) { //Actualizar peliculas
        Pelicula pelicula = peliculaRepository.findById(id).orElse(null);
        if (pelicula == null) return null;

        pelicula.setTitulo(peliculaActualizada.getTitulo());
        pelicula.setAnio(peliculaActualizada.getAnio());
        pelicula.setDuracion(peliculaActualizada.getDuracion());
        pelicula.setGenero(peliculaActualizada.getGenero());
        pelicula.setSinopsis(peliculaActualizada.getSinopsis());
        pelicula.setFechaEstreno(pelicula.getFechaEstreno());

        return peliculaRepository.save(pelicula);
    }

}
