package com.example.Moviesdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.Moviesdemo.model.Usuario;
import com.example.Moviesdemo.repository.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByUsername("Admin1").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("Admin1");
            admin.setCorreo("admin@movies.com");
            admin.setContrasena(passwordEncoder.encode("Admin123"));
            admin.setRole("ROLE_ADMIN");
            usuarioRepository.save(admin);
            System.out.println("Admin creado: Admin1 / Admin123");
        }
    }
}
