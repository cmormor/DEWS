package com.app.Servelet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.app.DAO.EmpresaDAO;
import com.app.Exceptions.DatosNoCorrectosException;
import com.app.Laboral.Empleado;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/empleados")
public class MostarEmpleadosServelet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmpresaDAO empleadoDAO = new EmpresaDAO();
		try {
			List<Empleado> empleados = empleadoDAO.getEmpleados();
			request.setAttribute("empleados", empleados);
			RequestDispatcher dispatcher = request.getRequestDispatcher("empleados.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException | DatosNoCorrectosException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error al acceder a la base de datos. Inténtalo de nuevo más tarde.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
			dispatcher.forward(request, response);
		}
	}
}