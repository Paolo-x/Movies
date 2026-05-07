package com.example.Moviesdemo.Repository;


import com.example.Moviesdemo.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    //Nuevos metodos void para poder borrar usuarios segun su Username y su Correo.
     void deleteByUsername(String username);
     void deleteByCorreo(String correo);
  
}