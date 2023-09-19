package com.backend.EMS.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Configuration class for initializing the ModelMapper bean.
 */
@Configuration
public class ModelMapperConfig {
/**
     * Creates and configures a ModelMapper bean.
     *
     * @return The initialized ModelMapper instance.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
