package com.app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.app.connection.DBConnection;
import com.app.exceptions.DatosNoCorrectosException;
import com.app.laboral.Empleado;
import com.app.laboral.Nomina;

public class EmpresaDAO {

	public List<Empleado> getEmpleados() throws SQLException, DatosNoCorrectosException {

		List<Empleado> empleados = new ArrayList<>();
		String consulta = "SELECT * FROM empleados";

		try (Connection con = DBConnection.getInstance().getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(consulta)) {

			while (rs.next()) {
				Empleado e = new Empleado(rs.getString("dni"), rs.getString("nombre"), rs.getString("sexo").charAt(0),
						rs.getInt("categoria"), rs.getInt("anyos_trabajados"));
				empleados.add(e);
			}
		}
		return empleados;
	}

	public Empleado getEmpleadoByDNI(String dni) throws SQLException, DatosNoCorrectosException {
		String consulta = "SELECT * FROM empleados WHERE dni = ?";
		try (Connection con = DBConnection.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(consulta)) {
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Empleado(rs.getString("dni"), rs.getString("nombre"), rs.getString("sexo").charAt(0),
						rs.getInt("categoria"), rs.getInt("anyos_trabajados"));
			} else {
				return null;
			}
		}
	}

	public int getSalarioByDni(String dni) throws SQLException, DatosNoCorrectosException {
		String consulta = "SELECT sueldo FROM nominas WHERE dni = ?";
		try (Connection con = DBConnection.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(consulta)) {
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("sueldo");
			} else {
				throw new DatosNoCorrectosException("El DNI del empleado no existe.");
			}
		}
	}

	public List<Empleado> buscarEmpleados(String dni, String nombre, String sexo, String categoria, String anyos)
	        throws SQLException, DatosNoCorrectosException {
	    List<Empleado> empleados = new ArrayList<>();
	    StringBuilder consulta = new StringBuilder("SELECT * FROM empleados WHERE 1=1");

	    // Construir la consulta SQL basada en los parámetros recibidos
	    if (dni != null && !dni.isEmpty()) {
	        consulta.append(" AND dni = ?");
	    }
	    if (nombre != null && !nombre.isEmpty()) {
	        consulta.append(" AND nombre LIKE ?");
	    }
	    if (sexo != null && !sexo.isEmpty()) {
	        consulta.append(" AND sexo = ?");
	    }
	    if (categoria != null && !categoria.isEmpty()) {
	        consulta.append(" AND categoria = ?");
	    }
	    if (anyos != null && !anyos.isEmpty()) {
	        consulta.append(" AND anyos_trabajados = ?");
	    }

	    try (Connection con = DBConnection.getInstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(consulta.toString())) {
	        
	        int index = 1;

	        if (dni != null && !dni.isEmpty()) {
	            ps.setString(index++, dni);
	        }
	        if (nombre != null && !nombre.isEmpty()) {
	            ps.setString(index++, "%" + nombre + "%");
	        }
	        if (sexo != null && !sexo.isEmpty()) {
	            ps.setString(index++, sexo);
	        }
	        if (categoria != null && !categoria.isEmpty()) {
	            try {
	                ps.setInt(index++, Integer.parseInt(categoria));
	            } catch (NumberFormatException e) {
	                throw new DatosNoCorrectosException("La categoría debe ser un número entero.");
	            }
	        }
	        if (anyos != null && !anyos.isEmpty()) {
	            try {
	                ps.setInt(index++, Integer.parseInt(anyos));
	            } catch (NumberFormatException e) {
	                throw new DatosNoCorrectosException("Los años trabajados deben ser un número entero.");
	            }
	        }

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Empleado e = new Empleado(
	                    rs.getString("dni"),
	                    rs.getString("nombre"),
	                    rs.getString("sexo").charAt(0),
	                    rs.getInt("categoria"),
	                    rs.getInt("anyos_trabajados")
	            );
	            empleados.add(e);
	        }

	        if (empleados.isEmpty()) {
	            return empleados;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error en la búsqueda de empleados", e);
	    }

	    return empleados;
	}



	public void updateEmpleado(Empleado empleado) throws SQLException {
		String updateConsulta = "UPDATE empleados SET nombre = ?, sexo = ?, categoria = ?, anyos_trabajados = ? WHERE dni = ?";
		try (Connection con = DBConnection.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(updateConsulta)) {
			ps.setString(1, empleado.getNombre());
			ps.setString(2, String.valueOf(empleado.getSexo()));
			ps.setInt(3, empleado.getCategoria());
			ps.setInt(4, empleado.getAnyos());
			ps.setString(5, empleado.getDni());
			ps.executeUpdate();
		}
	}

	public void updateSalario(Empleado empleado) throws SQLException {
		Nomina nomina = new Nomina();
		int nuevoSalario = nomina.sueldo(empleado);

		String consulta = "UPDATE nominas SET sueldo = ? WHERE dni = ?";
		try (Connection con = DBConnection.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(consulta)) {

			ps.setInt(1, nuevoSalario);
			ps.setString(2, empleado.getDni());
			ps.executeUpdate();
		}
	}
}
