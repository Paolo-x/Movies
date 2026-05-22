package com.example.Moviesdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Moviesdemo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca un usuario por su username (usado para verificar existencia antes de borrar)
    Optional<Usuario> findByUsername(String username);

    // Busca un usuario por su correo (usado para verificar existencia antes de borrar)
    Optional<Usuario> findByCorreo(String correo);

    void deleteByUsername(String username);
    void deleteByCorreo(String correo);

}