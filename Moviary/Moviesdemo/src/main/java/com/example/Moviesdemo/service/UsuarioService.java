package com.example.Moviesdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Moviesdemo.dto.UsuarioResponseDTO;
import com.example.Moviesdemo.model.Usuario;
import com.example.Moviesdemo.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO agregarUsuario(Usuario usuario){
        return mapearADTO(usuarioRepository.save(usuario));
    }

    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream().map(this::mapearADTO).toList();
    }

  public void deleteUsuarioUsername(String username){ //Eliminar usuario por username
        usuarioRepository.deleteByUsername(username);
    }

     public void deleteUsuarioCorreo(String correo){ //Eliminar usuario por correo
        usuarioRepository.deleteByCorreo(correo);
    }

    public UsuarioResponseDTO obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).map(this::mapearADTO).orElse(null);
    }

        public UsuarioResponseDTO actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) return null;

       usuario.setUsername(usuarioActualizado.getUsername());
       usuario.setCorreo(usuarioActualizado.getCorreo());
       usuario.setContrasena(usuarioActualizado.getContrasena());

        return mapearADTO(usuarioRepository.save(usuario));
    }

    private UsuarioResponseDTO mapearADTO(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getId(), usuario.getUsername(), usuario.getCorreo(), usuario.getFechaRegistro());
    }
    
}
