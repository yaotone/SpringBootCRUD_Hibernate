package ru.yaotone.hibernateproj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/styles/**").permitAll()
                        .requestMatchers("/users/register").permitAll()
                        .requestMatchers(HttpMethod.GET ,"/users").authenticated()
                        .requestMatchers(HttpMethod.GET, "/users/*/update", "/users/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST ,"/users").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH ,"/users/*").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE ,"/users/**").hasAnyAuthority("ROLE_ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.loginPage("/login").usernameParameter("email").permitAll()
                        .defaultSuccessUrl("/users"))
                .logout(logout -> logout.permitAll())
                .build();
    }


}
