package com.gym.gimnasio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //Configurar rutas y reglas de seguridad
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
           .authorizeHttpRequests(auth -> auth
            
             //Permite el registro y el acceso a la consola H2
             .requestMatchers("/registro/**", "/h2-console/**", "/css/**").permitAll()
             //La administración y entrenamiento requiere rol ENTRENADOR
             .requestMatchers("/entrenador/**").hasRole("ENTRENADOR")
             //Las demás rutas neceistan que el usuario haya iniciado sesión
             .anyRequest().authenticated()
            )

            //Configurar el inicio de sesión utilizando el correo
            .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/inicio", true)
                .permitAll()
            )

            //Configuración del cierre de sesión 
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )

            //Permite el acceso a la consola H2
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

            return http.build();

    }

    //Crear el cifrador de contraseñas usando BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
