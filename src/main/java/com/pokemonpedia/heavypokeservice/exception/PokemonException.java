package com.pokemonpedia.heavypokeservice.exception;

public class PokemonException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public PokemonException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
