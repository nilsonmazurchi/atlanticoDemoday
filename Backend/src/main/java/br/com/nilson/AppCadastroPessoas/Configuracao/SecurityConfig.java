package br.com.nilson.AppCadastroPessoas.Configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
             .requestMatchers("/", "/index.html", "/favicon.ico", "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.svg", "/**/*.woff2", "/h2-console/**", "/pessoas/**").permitAll()
             .anyRequest().authenticated()
          )
          .headers(headers -> headers.frameOptions(frame -> frame.disable()));
        return http.build();
    }
}