package com.example.Moviesdemo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Moviesdemo.Model.Usuario;
import com.example.Moviesdemo.Service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @PostMapping
    public Usuario agregarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.agregarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }

    @DeleteMapping("/username/{username}")
    public void eliminarUsuarioPorUsername(@PathVariable String username) {
        usuarioService.deleteUsuarioUsername(username);
    }

    @DeleteMapping("/correo/{correo}")
    public void eliminarUsuarioPorCorreo(@PathVariable String correo) {
        usuarioService.deleteUsuarioCorreo(correo);
    }
}