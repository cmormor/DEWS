package com.app.Servelet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.app.DAO.EmpresaDAO;
import com.app.Exceptions.DatosNoCorrectosException;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/resultadoSalario")
public class BuscarSalarioServelet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dni = request.getParameter("dni");
		EmpresaDAO empresaDAO = new EmpresaDAO();

		try {
			int salario = empresaDAO.getSalarioByDni(dni);
			if (salario > 0) {
				request.setAttribute("salario", salario);
				RequestDispatcher dispatcher = request.getRequestDispatcher("resultadoSalario.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Empleado no encontrado");
			}
		} catch (DatosNoCorrectosException e) {
			request.setAttribute("errorMessage", "El DNI ingresado no corresponde a ningún empleado.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error al acceder a la base de datos. Inténtalo de nuevo más tarde.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
			dispatcher.forward(request, response);
		}
	}
}
