package com.app.exceptions;

/**
 * Clase de Excepciones
 */

public class DatosNoCorrectosException extends Exception{

	/**
	 * Contrctor para mensaje de la excepción
	 */
	
	public DatosNoCorrectosException() {
		super();
	}

	/**
	 * Mensaje que se muestra
	 * @param message
	 * Contrctor para mensaje de la excepción
	 */
	
	public DatosNoCorrectosException(String message) {
		super(message);
	}
	
}
