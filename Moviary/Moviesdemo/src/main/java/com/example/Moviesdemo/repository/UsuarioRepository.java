package com.example.Moviesdemo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Moviesdemo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    //Nuevos metodos void para poder borrar usuarios segun su Username y su Correo.
     void deleteByUsername(String username);
     void deleteByCorreo(String correo);
  
}