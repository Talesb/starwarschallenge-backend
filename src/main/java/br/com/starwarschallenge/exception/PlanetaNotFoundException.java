package br.com.starwarschallenge.exception;

import java.io.Serializable;

public class PlanetaNotFoundException extends Exception implements Serializable {

	private static final long serialVersionUID = -3254611742742744994L;

	public PlanetaNotFoundException() {
		super("Planeta Não encontrado");
	}

	public PlanetaNotFoundException(String message) {
		super(message);
	}

	public PlanetaNotFoundException(String message, Exception ex) {
		super(message, ex);
	}

}