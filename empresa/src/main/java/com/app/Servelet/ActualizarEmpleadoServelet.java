package com.app.Servelet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.app.DAO.EmpresaDAO;
import com.app.Exceptions.DatosNoCorrectosException;
import com.app.Laboral.Empleado;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/actualizarEmpleado")
public class ActualizarEmpleadoServelet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String sexoStr = request.getParameter("sexo");
		String categoriaStr = request.getParameter("categoria");
		String anyosStr = request.getParameter("anyos");

		EmpresaDAO empleadoDAO = new EmpresaDAO();

		try {
			Empleado empleadoExistente = empleadoDAO.getEmpleadoByDNI(dni);
			if (empleadoExistente == null) {
				request.setAttribute("errorMessage", "Empleado no encontrado. Verifica el DNI.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
				dispatcher.forward(request, response);
				return;
			}

			Empleado empleadoActualizado = new Empleado(dni,
					nombre != null && !nombre.isEmpty() ? nombre : empleadoExistente.getNombre(),
					sexoStr != null && !sexoStr.isEmpty() ? sexoStr.charAt(0) : empleadoExistente.getSexo(),
					categoriaStr != null && !categoriaStr.isEmpty() ? Integer.parseInt(categoriaStr)
							: empleadoExistente.getCategoria(),
					anyosStr != null && !anyosStr.isEmpty() ? Integer.parseInt(anyosStr)
							: empleadoExistente.getAnyos());

			empleadoDAO.updateEmpleado(empleadoActualizado);
			empleadoDAO.updateSalario(empleadoActualizado);
			response.sendRedirect("empleados");

		} catch (SQLException | DatosNoCorrectosException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
			dispatcher.forward(request, response);
		}
	}
}
