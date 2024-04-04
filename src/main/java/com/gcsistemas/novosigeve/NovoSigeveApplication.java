package com.gcsistemas.novosigeve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gcsistemas.novosigeve.util.Ambiente;
import com.gcsistemas.novosigeve.util.PropriedadesAplicacao;

@SpringBootApplication
@EnableWebMvc
public class NovoSigeveApplication implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
	}
	
	private static final Logger logger = LoggerFactory.getLogger(NovoSigeveApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(NovoSigeveApplication.class, args);
		logger.info("===> {}", PropriedadesAplicacao.getInstance());
		logger.info("===> {}", Ambiente.getAmbiente());
		
		logger.info("APP INICIADA");
	}
	
}
