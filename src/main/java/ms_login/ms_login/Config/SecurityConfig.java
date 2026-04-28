package ms_login.ms_login.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. Deshabilitamos la protección CSRF (necesario para APIs REST con React)
            .csrf(csrf -> csrf.disable())
            
            // 2. Le decimos a Spring qué rutas están permitidas sin estar logueado
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/login").permitAll() // Liberamos tu endpoint
                .anyRequest().authenticated() // Bloqueamos todo lo demás
            );
            
        return http.build();
    }
}