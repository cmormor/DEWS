package com.app.Laboral;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

/**
 * Clase Main
 */

public class Main {

	/**
	 * Constructor por defecto
	 */

	public Main() {
		super();
	}

	/**
	 * Menú del programa
	 * 
	 * @param args args
	 * @throws DatosNoCorrectosException Excepción por si los datos no son correctos
	 * @throws SQLException              SQLException
	 * @throws IOException               IOException
	 */

	public static void main(String[] args) throws DatosNoCorrectosException, SQLException, IOException {

		procesarEmpleados();

		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		CalculaNominaBD bd = new CalculaNominaBD();

		while (!salir) {
			System.out.println("Menú:");
			System.out.println("1. Mostrar información de todos los empleados");
			System.out.println("2. Mostrar salario de un empleado por DNI");
			System.out.println("3. Modificar datos de un empleado");
			System.out.println("4. Recalcular y actualizar sueldo de un empleado");
			System.out.println("5. Recalcular y actualizar sueldos de todos los empleados");
			System.out.println("6. Realizar copia de seguridad");
			System.out.println("7. Salir");

			int opcion = sc.nextInt();
			sc.nextLine();
			String dni;

			switch (opcion) {
			case 1:

				bd.leerEmpleadosDesdeBD();
				break;

			case 2:

				System.out.println("Dime el DNI: ");
				dni = sc.nextLine();
				mostrarSalarioPorDNI(dni);
				break;

			case 3:

				System.out.println("Dime el DNI: ");
				dni = sc.nextLine();
				modificarEmpleado(dni);
				break;

			case 4:

				System.out.println("Dime el DNI: ");
				dni = sc.nextLine();
				recalcularSueldoEmpleado(dni);
				break;

			case 5:

				CalculaNominaBD.actualizarSueldos(bd.leerEmpleadosDesdeBD());

				break;

			case 6:

				realizarCopiaSeguridad();
				break;

			case 7:

				salir = true;
				System.out.println("Adios!");
				break;

			default:

				System.out.println("Opción no válida.");

			}
		}

	}

	/**
	 * Método para crear un archivo con el dni y el sueldo de los empleados que haya
	 * en el archivo de texto de entrada
	 * 
	 * @param archivoEntrada Archivo del que se cogen los datos
	 * @param archivoSalida  Archivo que se crea
	 * @throws IOException               IOException
	 * @throws DatosNoCorrectosException Excepción por si los datos no son correctos
	 */

	public static void procesarEmpleados() throws IOException, DatosNoCorrectosException {

		BufferedReader br = new BufferedReader(new FileReader("empleados.txt"));
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("salario.dat"));

		String linea = br.readLine();
		while (linea != null) {

			String[] datos = linea.split(",");

			String nombre = datos[0];
			String dni = datos[1];
			char sexo = datos[2].charAt(0);
			int categoria = Integer.parseInt(datos[3]);
			int anyosTrabajados = Integer.parseInt(datos[4]);

			Empleado e = new Empleado(nombre, dni, sexo, categoria, anyosTrabajados);

			Nomina n = new Nomina();
			int sueldo = n.sueldo(e);

			dos.writeUTF(e.dni);
			dos.writeInt(sueldo);

			linea = br.readLine();

		}

		System.out.println("Archivo binario creado con exito.");

	}

	/**
	 * Método para mostrar el salario del empleado según su DNI
	 * 
	 * @param dni Dato para identifcar al empleado
	 * @throws SQLException SQLException
	 */

	public static void mostrarSalarioPorDNI(String dni) throws SQLException {

		String consulta = "select dni, sueldo from nominas where dni = '" + dni + "';";

		Connection conn = CalculaNominaBD.conectar();
		PreparedStatement ps = conn.prepareStatement(consulta);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			System.out.println("DNI: " + rs.getString("dni"));
			System.out.println("Sueldo: " + rs.getInt("sueldo"));

		} else {

			System.out.println("No se encontró un empleado con ese DNI.");

		}
	}

	/**
	 * Método para modificar cualquier campo del empleado según su DNI
	 * 
	 * @param dni Dato para identifcar al empleado
	 * @throws SQLException SQLException
	 */

	public static void modificarEmpleado(String dni) throws SQLException {

		Connection conn = CalculaNominaBD.conectar();
		PreparedStatement ps;
		String consulta;
		Scanner sc = new Scanner(System.in);

		System.out.println("Seleccione qué desea modificar: ");
		System.out.println("1. Nombre");
		System.out.println("2. DNI");
		System.out.println("3. Sexo");
		System.out.println("4. Categoría");
		System.out.println("5. Años trabajados");

		int opcion = sc.nextInt();
		sc.nextLine();

		switch (opcion) {
		case 1:
			System.out.print("Nuevo nombre: ");
			String nuevoNombre = sc.nextLine();

			consulta = "UPDATE empleados SET nombre = ? WHERE dni = ?";
			ps = conn.prepareStatement(consulta);
			ps.setString(1, nuevoNombre);
			ps.setString(2, dni);

			int filasActualizadasNombre = ps.executeUpdate();

			if (filasActualizadasNombre > 0) {
				System.out.println("Nombre actualizado correctamente.");
			} else {
				System.out.println("No se encontró un empleado con ese DNI.");
			}
			break;

		case 2:
			System.out.print("Nuevo DNI: ");
			String nuevoDNI = sc.nextLine();

			consulta = "UPDATE empleados SET dni = ? WHERE dni = ?";
			ps = conn.prepareStatement(consulta);
			ps.setString(1, nuevoDNI);
			ps.setString(2, dni);

			int filasActualizadasDNI = ps.executeUpdate();

			if (filasActualizadasDNI > 0) {
				System.out.println("DNI actualizado correctamente.");
			} else {
				System.out.println("No se encontró un empleado con ese DNI.");
			}
			break;

		case 3:
			System.out.print("Nuevo Sexo (M/F): ");
			char nuevoSexo = sc.nextLine().charAt(0);

			consulta = "UPDATE empleados SET sexo = ? WHERE dni = ?";
			ps = conn.prepareStatement(consulta);
			ps.setString(1, String.valueOf(nuevoSexo));
			ps.setString(2, dni);

			int filasActualizadasSexo = ps.executeUpdate();

			if (filasActualizadasSexo > 0) {
				System.out.println("Sexo actualizado correctamente.");
			} else {
				System.out.println("No se encontró un empleado con ese DNI.");
			}
			break;

		case 4:
			System.out.print("Nueva Categoría (1-10): ");
			int nuevaCategoria = sc.nextInt();

			consulta = "UPDATE empleados SET categoria = ? WHERE dni = ?";
			ps = conn.prepareStatement(consulta);
			ps.setInt(1, nuevaCategoria);
			ps.setString(2, dni);

			int filasActualizadasCategoria = ps.executeUpdate();

			if (filasActualizadasCategoria > 0) {
				System.out.println("Categoría actualizada correctamente.");
			} else {
				System.out.println("No se encontró un empleado con ese DNI.");
			}
			break;

		case 5:
			System.out.print("Actualización de años trabajados: ");
			int nuevosAnyos = sc.nextInt();

			consulta = "UPDATE empleados SET anyos_trabajados = ? WHERE dni = ?";
			ps = conn.prepareStatement(consulta);
			ps.setInt(1, nuevosAnyos);
			ps.setString(2, dni);

			int filasActualizadasAnyos = ps.executeUpdate();

			if (filasActualizadasAnyos > 0) {
				System.out.println("Años trabajados actualizados correctamente.");
			} else {
				System.out.println("No se encontró un empleado con ese DNI.");
			}
			break;

		default:
			System.out.println("Opción no válida.");
			break;
		}

	}

	/**
	 * Método para recalcular el sueldo de un empleado
	 * 
	 * @param dni Dato para identifcar al empleado
	 * @throws DatosNoCorrectosException Excepción por si los datos no son correctos
	 * @throws SQLException              SQLException
	 */

	public static void recalcularSueldoEmpleado(String dni) throws DatosNoCorrectosException, SQLException {

		String consulta = "SELECT * FROM empleados WHERE dni = ?;";

		Connection conn = CalculaNominaBD.conectar();
		PreparedStatement ps = conn.prepareStatement(consulta);

		ps.setString(1, dni);
		ResultSet rs = ps.executeQuery();

		Empleado e = null;

		if (rs.next()) {

			e = new Empleado(rs.getString("nombre"), rs.getString("dni"), rs.getString("sexo").charAt(0),
					rs.getInt("categoria"), rs.getInt("anyos_trabajados"));
		}

		if (e != null) {
			String checkNomina = "SELECT * FROM nominas WHERE dni = ?;";
			ps = conn.prepareStatement(checkNomina);
			ps.setString(1, dni);
			rs = ps.executeQuery();

			Nomina n = new Nomina();
			int sueldoRecalculado = n.sueldo(e);

			if (rs.next()) {
				String update = "UPDATE nominas SET sueldo = ? WHERE dni = ?;";
				ps = conn.prepareStatement(update);
				ps.setInt(1, sueldoRecalculado);
				ps.setString(2, dni);
				ps.executeUpdate();
				System.out.println("Sueldo actualizado correctamente.");
			} else {
				String insert = "INSERT INTO nominas (dni, sueldo) VALUES (?, ?);";
				ps = conn.prepareStatement(insert);
				ps.setString(1, e.dni);
				ps.setInt(2, sueldoRecalculado);
				ps.executeUpdate();
				System.out.println("Sueldo insertado correctamente.");
			}

		} else {
			System.out.println("No se encontró un empleado con ese DNI.");
		}
	}

	/**
	 * Método para hacer copia de seguridad de la BBDD
	 * 
	 * @throws SQLException              SQLException
	 * @throws DatosNoCorrectosException Excepción por si los datos no son correctos
	 * @throws IOException               IOException
	 */

	public static void realizarCopiaSeguridad() throws SQLException, DatosNoCorrectosException, IOException {

		Connection conn = CalculaNominaBD.conectar();
		CalculaNominaBD bd = new CalculaNominaBD();

		Empleado[] empleados = bd.leerEmpleadosDesdeBD();

		BufferedWriter bw = new BufferedWriter(new FileWriter("backup_base_de_datos.sql"));

		for (Empleado emp : empleados) {

			bw.write(emp.nombre + emp.dni + emp.sexo + emp.getCategoria() + emp.anyos);
			bw.newLine();

		}

		System.out.println("Copia de seguridad realizada correctamente.");
	}

}
