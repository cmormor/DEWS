package com.app.Laboral;

/**
 * Clase Empleado que extiende de Persona
 */

public class Empleado extends Persona {
	
	/**
	 * Propiedad categoria (privada)
	 */
	private int categoria;
	/**
	 * Propiedad anyios
	 */
	public int anyos;
	
	/**
	 * Nombre Empleado
	 * @param nombre
	 * DNI Empleadp
	 * @param dni
	 * Sexo Empleado
	 * @param sexo
	 * Categoria Empleado
	 * @param categoria
	 * Anyios trabajados Empleado
	 * @param anyos
	 * Controlador de Excepciones
	 * @throws DatosNoCorrectosException
	 * Constructor con los datos de Empleado y Persona
	 * controlado por excepciones
	 */
	
	public Empleado(String nombre, String dni, char sexo, int categoria, int anyos) throws DatosNoCorrectosException{
		super(nombre, dni, sexo);
		
		if (nombre == null || nombre.isEmpty()) {
			throw new DatosNoCorrectosException("Datos no correctos");
		}
		
		if (dni == null || dni.isEmpty()) {
			throw new DatosNoCorrectosException("Datos no correctos");
		}
		
		if (categoria < 0 || categoria > 10) {
			throw new DatosNoCorrectosException("Datos no correctos");
		}
		
		if (anyos < 0) {
			throw new DatosNoCorrectosException("Datos no correctos");
		}
		
		
		this.categoria = categoria;
		this.anyos = anyos;
	}

	/**
	 * Nombre del Empleado
	 * @param nombre
	 * DNI del Empleadpp
	 * @param dni
	 * Sexo del Empleado
	 * @param sexo
	 * Controlado de Excepciones
	 * @throws DatosNoCorrectosException
	 * Constructor con los datos de Persona
	 * controlado por excepciones
	 */
	
	public Empleado(String nombre, String dni, char sexo) throws DatosNoCorrectosException {
		super(nombre, dni, sexo);
		
		if (nombre == null || nombre.isEmpty()) {
			throw new DatosNoCorrectosException("Datos no correctos");
		}
		
		if (dni == null || dni.isEmpty()) {
			throw new DatosNoCorrectosException("Datos no correctos");
		}
		
		this.categoria = 1;
		this.anyos = 0;
	}
	
	/**
	 * Categotia del Empleado
	 * @param categoria
	 * Método para establecer la categoria
	 */
	
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	/**
	 * Devuelve la categoria
	 * @return
	 * Método para que me devuelva la categoria
	 */
	
	public int getCategoria() {
		return categoria;
	}
	
	/**
	 * Devuelve los anyos incrementados a uno
	 * @return
	 * Método para incrementar los anyos trabajados
	 */

	public int incrAnyo() {
		return anyos ++;
	}
	
	/**
	 * @return
	 * Método que devuelve la información de Persona
	 */

	public String Imprime() {
		return "Empleado: Nombre = " + nombre + ", DNI = " + dni + ", Sexo = " + sexo + ", Años trabajados = " + anyos + ", Categoria = " + getCategoria();
	}
	
}
