package app.bank.configue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.support.AbstractTransactionStatus;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
                        auth -> auth.requestMatchers(
                                        "/",
                                        "v3/api-doc/**",
                                        "/swagger-ui/**",
                                        "/v2/api-docs/**",
                                        "/swagger/resources/**"
                                ).permitAll()
                                .requestMatchers("api/v1/auth/**").permitAll()
                                .requestMatchers("api/v1/files/**").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
