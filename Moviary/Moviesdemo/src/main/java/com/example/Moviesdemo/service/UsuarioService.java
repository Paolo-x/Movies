package com.example.Moviesdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Moviesdemo.model.Usuario;
import com.example.Moviesdemo.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario agregarUsuario(Usuario usuario){ //Agregar usuario

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() { //Listar Usuarios
        return usuarioRepository.findAll();
    }

  public void deleteUsuarioUsername(String username){ //Eliminar usuario por username
        usuarioRepository.deleteByUsername(username);
    }

     public void deleteUsuarioCorreo(String correo){ //Eliminar usuario por correo
        usuarioRepository.deleteByCorreo(correo);
    }

    public Usuario obtenerUsuarioPorId(Long id) { //Obtene Usuairo por id
        return usuarioRepository.findById(id).orElse(null);
    }

        public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) { //Actualizar Usuarios - (username-correo-contraseña)
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) return null;

       usuario.setUsername(usuarioActualizado.getUsername());
       usuario.setCorreo(usuarioActualizado.getCorreo());
       usuario.setContrasena(usuarioActualizado.getContrasena());

        return usuarioRepository.save(usuario);
    }





    //public boolean existeUsuario(Long id) // ver si el usuario Existe
    
}
