package com.example.Moviesdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Moviesdemo.controller.PeliculaController;
import com.example.Moviesdemo.controller.ResenaController;
import com.example.Moviesdemo.controller.UsuarioController;
import com.example.Moviesdemo.dto.UsuarioResponseDTO;
import com.example.Moviesdemo.model.Pelicula;
import com.example.Moviesdemo.model.Resena;
import com.example.Moviesdemo.model.Usuario;
import com.example.Moviesdemo.service.PeliculaService;
import com.example.Moviesdemo.service.ResenaService;
import com.example.Moviesdemo.service.UsuarioService;

@ExtendWith(MockitoExtension.class)
public class MoviesTests {
    
    @Mock 
    private PeliculaService peliculaService;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private ResenaService resenaService;

    @InjectMocks
    private PeliculaController peliculaController;

    @InjectMocks
    private UsuarioController usuarioController;

    @InjectMocks
    private ResenaController resenaController;

    @Test
    void crearPelicula_retorna201_cuandoExistePelicula(){
     

        Pelicula pelicula = new Pelicula(
            1L,                   
            "Memento",           
            2000,              
            113,                
            "Thriller",           
            "Un hombre persigue al asesino de su esposa", 
            2000,                
            null                  
        );

        when(peliculaService.agregarPelicula(pelicula)).thenReturn(pelicula);

        // Act - Ejecutar
        ResponseEntity<Pelicula> respuesta = peliculaController.agregarPelicula(pelicula);

        // Assert - Verificar
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals("Memento", respuesta.getBody().getTitulo());
        assertEquals(113, respuesta.getBody().getDuracion());
    }

    @Test
    void obtenerPeliculaPorId_retorna404_cuandoNoExiste() {
        when(peliculaService.obtenerPeliculaPorId(999L)).thenReturn(null);

        ResponseEntity<Pelicula> respuesta = peliculaController.obtenerPorId(999L);

        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
    }

    @Test
    void crearResena_retorna201_conDatosCorrectos() {
        Resena resena = new Resena();
        resena.setCalificacion(10);
        resena.setComentario("Excelente pelicula");
        resena.setFechaResena(20240521);

        when(resenaService.agregarResena(resena)).thenReturn(resena);

        ResponseEntity<Resena> respuesta = resenaController.agregarResena(resena);

        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals(10, respuesta.getBody().getCalificacion());
        assertEquals("Excelente pelicula", respuesta.getBody().getComentario());
    }

    @Test
    void crearUsuario_retorna201_conDatosCorrectos() {
        Usuario usuario = new Usuario();
        usuario.setUsername("test");
        usuario.setCorreo("test@example.com");
        usuario.setContrasena("12345678");

        UsuarioResponseDTO dto = new UsuarioResponseDTO(1L, "test", "test@example.com", 2026);

        when(usuarioService.agregarUsuario(usuario)).thenReturn(dto);

        ResponseEntity<UsuarioResponseDTO> respuesta = usuarioController.agregarUsuario(usuario);

        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals("test", respuesta.getBody().getUsername());
        assertEquals("test@example.com", respuesta.getBody().getCorreo());
    }

}
