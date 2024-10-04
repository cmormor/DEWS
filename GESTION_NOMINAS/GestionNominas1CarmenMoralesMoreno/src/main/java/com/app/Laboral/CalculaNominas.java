package com.app.Laboral;

/**
 * Clase CalculaNominas
 */

public class CalculaNominas {

	/**
	 * Constructor
	 */
	
	public CalculaNominas() {
		super();
	}

	/**
	 * Array de tipo String
	 * @param args
	 * Controlador de Excepciones
	 * @throws DatosNoCorrectosException
	 * Main
	 */
	
	public static void main(String[] args) throws DatosNoCorrectosException {

		Empleado e1 = new Empleado("James Cosling", "3200032G", 'M', 4, 7);
		Empleado e2 = new Empleado("Ada Lovelace", "32000031R", 'F');
		
		Escribe(e1);
		Escribe(e2);
		
		e2.incrAnyo();
		e1.setCategoria(9);
		
		System.out.println("-----------------------------------------------------");
	
		Escribe(e1);
		Escribe(e2);
	}
	
	private static void Escribe (Empleado e) {
		
		Nomina n = new Nomina();
		System.out.println(e.Imprime() + ", Sueldo = " + n.sueldo(e));
		
	}

}
