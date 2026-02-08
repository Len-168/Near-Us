package app.near.us.api_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final ApplicationApiKeyFilter applicationApiKeyFilter;

    public SecurityConfig(
        JwtAuthenticationFilter jwtFilter,
        ApplicationApiKeyFilter applicationApiKeyFilter
    ) {
        this.jwtFilter = jwtFilter;
        this.applicationApiKeyFilter = applicationApiKeyFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/login").permitAll()
            .requestMatchers("/api/auth/register").permitAll()
            .anyRequest().authenticated()
        )
        .addFilterBefore(applicationApiKeyFilter, UsernamePasswordAuthenticationFilter.class)// protect register , login
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);// JWT protect

        return http.build();
    }
}
