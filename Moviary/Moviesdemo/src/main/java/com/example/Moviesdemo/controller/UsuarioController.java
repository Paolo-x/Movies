package com.example.Moviesdemo.controller;

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

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        UsuarioResponseDTO usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> agregarUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.agregarUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        UsuarioResponseDTO actualizado = usuarioService.actualizarUsuario(id, usuario);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/username/{username}")
    public ResponseEntity<Void> eliminarUsuarioPorUsername(@PathVariable String username) {
        usuarioService.deleteUsuarioUsername(username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/correo/{correo}")
    public ResponseEntity<Void> eliminarUsuarioPorCorreo(@PathVariable String correo) {
        usuarioService.deleteUsuarioCorreo(correo);
        return ResponseEntity.noContent().build();
    }
}
