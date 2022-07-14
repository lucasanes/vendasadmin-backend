package com.gcsistemas.novosigeve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gcsistemas.novosigeve.util.Ambiente;
import com.gcsistemas.novosigeve.util.PropriedadesAplicacao;

@SpringBootApplication
public class NovoSigeveApplication {

	private static final Logger logger = LoggerFactory.getLogger(NovoSigeveApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(NovoSigeveApplication.class, args);
		logger.info("===> {}", PropriedadesAplicacao.getInstance());
		logger.info("===> {}", Ambiente.getAmbiente());
		
		logger.info("APP INICIADA");
	}
	
}
