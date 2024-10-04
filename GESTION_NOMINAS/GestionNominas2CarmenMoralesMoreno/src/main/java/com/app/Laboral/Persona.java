package com.app.Laboral;

/**
 * Clase Persona
 */

public class Persona {
	
	/**
	 * Nombre de Persona
	 */
	
	public String nombre;
	
	/**
	 * DNI de Persona
	 */
	public String dni;
	
	/**
	 * Sexo de Persona
	 */
	public char sexo;
	
	/**
	 * Establecer nombre
	 * @param nombre
	 * Establecer dni
	 * @param dni
	 * Establecer sexo
	 * @param sexo
	 * Constructor con todos los atributos de Persona
	 */
	
	public Persona(String nombre, String dni, char sexo){
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;
	}

	/**
	 * Establecer nombre
	 * @param nombre
	 * Establecer sexo
	 * @param sexo
	 * Constructor con nombre y sexo de Persona
	 */
	
	public Persona(String nombre, char sexo) {
		this.nombre = nombre;
		this.sexo = sexo;
	}
	
	/**
	 * Establecer dni
	 * @param dni
	 * Método para establecer el dni
	 */

	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Con el formato establecido
	 * @return
	 * Devuelve el nombre de la Persona y su dni
	 */
	
	public String Imprime() {
		return "Persona: Nombre=" + nombre + ", dni=" + dni;
	}
	
}
