package com.gcsistemas.vendasadmin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gcsistemas.vendasadmin.util.Ambiente;
import com.gcsistemas.vendasadmin.util.PropriedadesAplicacao;

@SpringBootApplication
@EnableWebMvc
public class vendasadminApplication implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
    registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
  }

  private static final Logger logger = LoggerFactory.getLogger(vendasadminApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(vendasadminApplication.class, args);
    logger.info("===> {}", PropriedadesAplicacao.getInstance());
    logger.info("===> {}", Ambiente.getAmbiente());

    logger.info("APP INICIADA");
  }

}
