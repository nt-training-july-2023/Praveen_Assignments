package com.backend.EMS.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration class for security-related beans.
 */
@Configuration
public class ConfigureSecurity {
    
    /**
     * Creates a BCrypt password encoder bean.
     *
     * @return The BCryptPasswordEncoder bean instance.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

