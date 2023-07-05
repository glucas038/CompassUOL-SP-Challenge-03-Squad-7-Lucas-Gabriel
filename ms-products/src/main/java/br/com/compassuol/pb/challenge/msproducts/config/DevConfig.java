package br.com.compassuol.pb.challenge.msproducts.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
