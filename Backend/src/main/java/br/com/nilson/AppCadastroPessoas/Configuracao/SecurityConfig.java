package br.com.nilson.AppCadastroPessoas.Configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index.html", "/static/**", "/**/*.js", "/**/*.css", "/**/*.ico").permitAll()
                .anyRequest().permitAll() // ou .authenticated() se tiver login
            )
            .csrf(csrf -> csrf.disable()); // opcional, dependendo da app
        return http.build();
    }
}
