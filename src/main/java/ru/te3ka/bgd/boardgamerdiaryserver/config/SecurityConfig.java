package ru.te3ka.bgd.boardgamerdiaryserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Конфигурация безопасности для настройки параметров безопасности веб-приложения.
 *
 * Этот класс содержит настройки для авторизации, аутентификации и защиты от CSRF.
 */
@EnableWebSecurity
@Configuration
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * Конфигурация фильтра безопасности для настройки параметров безопасности веб-приложения.
     *
     * @param httpSecurity Объект для настройки параметров безопасности HTTP.
     * @return Настроенный объект SecurityFilterChain.
     * @throws Exception В случае ошибки настройки безопасности.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers("/upload/**").permitAll()
                                .requestMatchers("/meetings/**").permitAll()
                                .requestMatchers("/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/**", "/h2-console/**", "/upload/wishlist/**", "/meetings/**"))
                .headers(headers -> headers.frameOptions().disable());
        return httpSecurity.build();
    }

    /**
     * Конфигурация сервиса пользовательских деталей для аутентификации пользователей.
     *
     * @return Объект UserDetailsService, управляющий пользовательскими данными в памяти.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build());
        return manager;
    }
}
