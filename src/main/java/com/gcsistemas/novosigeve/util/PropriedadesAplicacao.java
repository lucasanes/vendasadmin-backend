package com.gcsistemas.novosigeve.util;

import java.text.MessageFormat;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class PropriedadesAplicacao {

	private String sigla;
	
	private String ambiente;
	
	private String logsDir;
	
	private String resourcesDir;
	
	private String contexto;

	private String reservado;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}
	
	public static PropriedadesAplicacao getInstance() {
		return SpringContext.getBean(PropriedadesAplicacao.class);
	}

	public String getLogsDir() {
		return logsDir;
	}

	public void setLogsDir(String logsDir) {
		this.logsDir = logsDir;
	}

	public String getResourcesDir() {
		return resourcesDir;
	}

	public void setResourcesDir(String resourcesDir) {
		this.resourcesDir = resourcesDir;
	}

	public String getContexto() {
		return contexto;
	}

	public void setContexto(String contexto) {
		this.contexto = contexto;
	}

	public String toString() {
		return MessageFormat.format("sigla={0},ambiente={1},logsDir={2},resourcesDir={3},contexto={4},reservado={5}", 
				getSigla(), getAmbiente(), getLogsDir(), getResourcesDir(), getContexto(), getReservado());
	}

	public String getReservado() {
		return reservado;
	}

	public void setReservado(String reservado) {
		this.reservado = reservado;
	}

}