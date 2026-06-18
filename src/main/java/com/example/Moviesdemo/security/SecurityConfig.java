package com.example.Moviesdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Deshabilita CSRF (no es necesario en APIs REST stateless con JWT)
            .csrf(csrf -> csrf.disable())

            // Sin sesiones en servidor: cada request se autentica solo con el JWT
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Reglas de autorización por ruta y método HTTP
            .authorizeHttpRequests(auth -> auth
                // Endpoints públicos (no requieren token)
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()

                // TMDB: ambos roles pueden consultar la API externa
                .requestMatchers("/api/v1/tmdb/**").hasAnyRole("USER", "ADMIN")

                // Peliculas y Usuarios: solo ADMIN
                .requestMatchers("/api/v1/peliculas/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/usuarios/**").hasRole("ADMIN")

                // Resenas: USER puede crear, ambos pueden modificar/eliminar
                .requestMatchers(HttpMethod.POST, "/api/v1/resenas/**").hasRole("USER")
                .requestMatchers("/api/v1/resenas/**").hasAnyRole("USER", "ADMIN")

                .anyRequest().authenticated()
            )

            // Agrega el filtro JWT antes del filtro de autenticación por usuario/contraseña
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * BCrypt para encriptar contraseñas. Factor de costo 10 (por defecto).
     * NUNCA se guarda la contraseña en texto plano en la BD.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Expone el AuthenticationManager como bean para usarlo en AuthController.
     * Spring lo configura automáticamente usando UserDetailsServiceImpl y PasswordEncoder.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}