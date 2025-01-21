package alura.ForoHub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/login").permitAll()
                .requestMatchers("/home").permitAll() // Agregar esta línea
                .anyRequest().authenticated()
        );
        http.formLogin(formLogin -> formLogin
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
        );
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("usuario")
                .password(passwordEncoder().encode("contraseña"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(Collections.singleton(user));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}