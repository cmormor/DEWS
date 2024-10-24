package com.app.Servelet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.app.DAO.EmpresaDAO;
import com.app.Exceptions.DatosNoCorrectosException;
import com.app.Laboral.Empleado;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/buscarEmpleado")
public class BuscarEmpleadoServelet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String sexo = request.getParameter("sexo");
		String categoria = request.getParameter("categoria");
		String anyos = request.getParameter("anyos");

		EmpresaDAO empleadoDAO = new EmpresaDAO();
		List<Empleado> empleados = new ArrayList<>();

		try {
			empleados = empleadoDAO.buscarEmpleados(dni, nombre, sexo, categoria, anyos);
			request.setAttribute("empleados", empleados);
			RequestDispatcher dispatcher = request.getRequestDispatcher("resultadoBusqueda.jsp");
			dispatcher.forward(request, response);
		} catch (DatosNoCorrectosException e) {
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error al buscar empleados. Revisa los datos ingresados.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
			dispatcher.forward(request, response);
		}
	}
}