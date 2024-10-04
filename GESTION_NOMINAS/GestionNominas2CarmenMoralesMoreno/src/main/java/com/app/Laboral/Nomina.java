package com.app.Laboral;

/**
 * Clase Nomina
 */

public class Nomina {
	
	/**
	 * Contructor
	 */
	
	public Nomina() {
		super();
	}

	/**
	 * Array de sueldos base
	 */

	private static final int SUELDO_BASE[] = {5000, 7000, 9000, 11000, 13000, 15000, 17000, 19000, 21000, 23000};
	
	/**
	 * paramentro Empleado
	 * @param e
	 * Devolver información empleado
	 * @return
	 * Método que te pide un empleado para calcular y devolver su sueldo
	 */
	
	public int sueldo (Empleado e) {
		
		int sueldo = SUELDO_BASE[e.getCategoria() - 1] + (5000*e.anyos);
		return sueldo;
		
	}
	
}
