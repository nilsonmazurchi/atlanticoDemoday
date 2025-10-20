// package br.com.nilson.AppCadastroPessoas.Configuracao;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable()) // Desabilita CSRF para simplificar (opcional)
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers(
//                     "/",
//                     "/index.html",
//                     "/favicon.ico",
//                     "/**/*.css",
//                     "/**/*.js",
//                     "/**/*.png",
//                     "/**/*.jpg",
//                     "/**/*.svg",
//                     "/**/*.woff2",
//                     "/**/*.woff",
//                     "/**/*.ttf",
//                     "/pessoas/**",
//                     "/h2-console/**"
//                 ).permitAll()
//                 .anyRequest().authenticated()
//             )
//             .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Necessário para o H2 Console
//             .formLogin(login -> login.disable()) // Desabilita tela de login padrão
//             .httpBasic(basic -> basic.disable()); // Desabilita autenticação básica

//         return http.build();
//     }
// }