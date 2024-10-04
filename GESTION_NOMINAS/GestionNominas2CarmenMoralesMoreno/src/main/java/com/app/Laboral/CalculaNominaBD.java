package com.app.Laboral;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Clase con los métodos para la BBDD
 */

public class CalculaNominaBD {

	/**
	 * Constructor por defecto
	 */

	public CalculaNominaBD() {
		super();
	}

	/**
	 * Método para conectarnos a la BBDD
	 * 
	 * @return Devuelve la conexión
	 * @throws SQLException SQLException
	 */

	public static Connection conectar() throws SQLException {

		String url = "jdbc:mariadb://localhost:3306/gestionnominas";
		String user = "root";
		String password = "123456";

		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * Método para leer los empleados desde la BBDD
	 * 
	 * @return Un array de empleados
	 * @throws SQLException              SQLException
	 * @throws DatosNoCorrectosException Excepción por si los datos no son correctos
	 */

	public Empleado[] leerEmpleadosDesdeBD() throws SQLException, DatosNoCorrectosException {

		ArrayList<Empleado> listaEmpleados = new ArrayList<>();
		String consulta = "select * from Empleados;";

		Connection conn = conectar();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(consulta);

		int i = 0;
		while (rs.next()) {

			String nombre = rs.getString("nombre");
			String dni = rs.getString("dni");
			char sexo = rs.getString("sexo").charAt(0);
			int categoria = rs.getInt("categoria");
			int anyosTrabajados = rs.getInt("anyos_trabajados");

			listaEmpleados.add(new Empleado(nombre, dni, sexo, categoria, anyosTrabajados));

			System.out.println(nombre + ", " + dni + ", " + sexo + ", " + categoria + ", " + anyosTrabajados + " listo");

		}

		return listaEmpleados.toArray(new Empleado[0]);
	}

	/**
	 * Método para actualizar sueldos
	 * 
	 * @param empleados Array de empleados
	 * @throws SQLException SQLException
	 */

	public static void actualizarSueldos(Empleado[] empleados) throws SQLException {

		String consulta = "UPDATE nominas SET sueldo = ? WHERE dni = ?;";

		Connection conn = conectar();
		PreparedStatement ps = conn.prepareStatement(consulta);
		Nomina n = new Nomina();

		for (Empleado emp : empleados) {
			
			ps.setInt(1, n.sueldo(emp));
			ps.setString(2, emp.dni);
			ps.executeUpdate();
		}
	}

	/**
	 * Método para dar de alta a un empleado
	 * 
	 * @param e Un empleado
	 * @throws SQLException              SQLException
	 * @throws DatosNoCorrectosException Excepción por si los datos no son correctos
	 */

	public static void altaEmpleado(Empleado e) throws SQLException, DatosNoCorrectosException {

		String verificarEmpleado = "SELECT dni FROM Empleados WHERE dni = ?;";
		String consultaEmpleado = "INSERT INTO Empleados (nombre, dni, sexo, categoria, anyos_trabajados) VALUES (?, ?, ?, ?, ?);";
		String consultaNomina = "INSERT INTO Nominas (dni, sueldo) VALUES (?, ?)";

		Connection conn = CalculaNominaBD.conectar();

		PreparedStatement psVerificar = conn.prepareStatement(verificarEmpleado);
		PreparedStatement psEmpleados = conn.prepareStatement(consultaEmpleado);
		PreparedStatement psNominas = conn.prepareStatement(consultaNomina);
		psVerificar.setString(1, e.dni);
		ResultSet rs = psVerificar.executeQuery();

		if (rs.next()) {
			System.out.println("El empleado con DNI " + e.dni + " ya existe en la base de datos.");
		} else {
			psEmpleados.setString(1, e.nombre);
			psEmpleados.setString(2, e.dni);
			psEmpleados.setString(3, String.valueOf(e.sexo));
			psEmpleados.setInt(4, e.getCategoria());
			psEmpleados.setInt(5, e.anyos);
			psEmpleados.executeUpdate();

			Nomina n = new Nomina();
			psNominas.setString(1, e.dni);
			psNominas.setDouble(2, n.sueldo(e));
			psNominas.executeUpdate();

			System.out.println("Empleado y nómina añadidos correctamente.");
		}
	}

	/**
	 * Método para dar a varios empleados a la vez a través de un archivo
	 * 
	 * @param nombreArchivo Nombre del archivo donde están los datos de los
	 *                      empleados
	 * @throws SQLException              SQLException
	 * @throws DatosNoCorrectosException Excepción por si los datos no son correctos
	 * @throws NumberFormatException     NumberFormatException
	 * @throws IOException               IOException
	 */

	public static void altaEmpleadosDesdeArchivo(String nombreArchivo)
			throws SQLException, DatosNoCorrectosException, NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));

		String linea = br.readLine();
		while (linea != null) {

			String[] datos = linea.split(",");

			String nombre = datos[0];
			String dni = datos[1];
			char sexo = datos[2].charAt(0);
			int categoria = Integer.parseInt(datos[3]);
			int anyosTrabajados = Integer.parseInt(datos[4]);

			Empleado emp = new Empleado(nombre, dni, sexo, categoria, anyosTrabajados);
			altaEmpleado(emp);

			linea = br.readLine();
		}
	}

}
