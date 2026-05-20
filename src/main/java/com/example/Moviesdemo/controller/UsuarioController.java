package com.example.Moviesdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Moviesdemo.dto.UsuarioResponseDTO;
import com.example.Moviesdemo.model.Usuario;
import com.example.Moviesdemo.service.UsuarioService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class); // Logger SLF4J

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        log.info("listarUsuarios"); // Log al listar usuarios
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        log.info("obtenerUsuarioPorId id={}", id); // Log con id del usuario
        UsuarioResponseDTO usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario == null) {
            log.warn("Usuario no encontrado id={}", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> agregarUsuario(@Valid @RequestBody Usuario usuario) {
        log.info("agregarUsuario username={}", usuario.getUsername()); // Log del username creado
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.agregarUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        log.info("actualizarUsuario id={}", id); // Log al actualizar usuario
        UsuarioResponseDTO actualizado = usuarioService.actualizarUsuario(id, usuario);
        if (actualizado == null) {
            log.warn("Usuario no encontrado para actualizar id={}", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/username/{username}")
    public ResponseEntity<Void> eliminarUsuarioPorUsername(@PathVariable String username) {
        log.info("eliminarUsuarioPorUsername username={}", username); // Log al eliminar por username
        usuarioService.deleteUsuarioUsername(username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/correo/{correo}")
    public ResponseEntity<Void> eliminarUsuarioPorCorreo(@PathVariable String correo) {
        log.info("eliminarUsuarioPorCorreo correo={}", correo); // Log al eliminar por correo
        usuarioService.deleteUsuarioCorreo(correo);
        return ResponseEntity.noContent().build();
    }
}
