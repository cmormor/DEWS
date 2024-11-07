package com.app.laboral;

/**
 * Clase Persona
 */
public class Persona {
	
	/**
	 * Nombre de Persona
	 */
	private String nombre;
	
	/**
	 * DNI de Persona
	 */
	private String dni;
	
	/**
	 * Sexo de Persona
	 */
	private char sexo;
	
	// Constructor con todos los atributos de Persona
	public Persona(String nombre, String dni, char sexo){
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;
	}

	// Constructor con nombre y sexo de Persona
	public Persona(String nombre, char sexo) {
		this.nombre = nombre;
		this.sexo = sexo;
	}
	
	// Getters para las propiedades
	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	public char getSexo() {
		return sexo;
	}
	
	// Método para imprimir
	public String Imprime() {
		return "Persona: Nombre=" + nombre + ", dni=" + dni;
	}
}
