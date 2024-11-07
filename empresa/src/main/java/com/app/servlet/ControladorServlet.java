package com.app.servlet;

import com.app.dao.EmpresaDAO;
import com.app.exceptions.DatosNoCorrectosException;
import com.app.laboral.Empleado;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/controlador")
public class ControladorServlet extends HttpServlet {

	private EmpresaDAO empresaDAO;

	@Override
	public void init() throws ServletException {
		empresaDAO = new EmpresaDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | DatosNoCorrectosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | DatosNoCorrectosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DatosNoCorrectosException {
		String action = request.getParameter("action");

		if (action == null) {
			response.sendRedirect("index.jsp");
			return;
		}

		try {
			switch (action) {
			case "buscarEmpleado":
			    buscarEmpleado(request, response);
			    break;
			case "mostrarSalarios":
				mostrarSalarios(request, response);
				break;
			case "editarEmpleado":
				editarEmpleado(request, response);
				break;
			case "mostrarEmpleados":
				mostrarEmpleados(request, response);
				break;
			default:
				response.sendRedirect("index.jsp");
				break;
			}
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
			request.setAttribute("error", "Ocurrió un error al procesar la solicitud: " + e.getMessage());
			request.getRequestDispatcher("errores.jsp").forward(request, response);
		}
	}

	protected void buscarEmpleado(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException, DatosNoCorrectosException, SQLException {

	    String dni = request.getParameter("dni");
	    String nombre = request.getParameter("nombre");
	    String sexo = request.getParameter("sexo");
	    String categoria = request.getParameter("categoria");
	    String anyos = request.getParameter("anyos");

	    if (dni == null && nombre == null && sexo == null && categoria == null && anyos == null) {
	        request.getRequestDispatcher("buscarEmpleado.jsp").forward(request, response);
	        return;
	    }

	    EmpresaDAO empresaDAO = new EmpresaDAO();
	    List<Empleado> empleados = empresaDAO.buscarEmpleados(dni, nombre, sexo, categoria, anyos);

	    if (empleados.isEmpty()) {
	        request.setAttribute("errorMensaje", "No se encontraron empleados con los criterios de búsqueda.");
	        request.getRequestDispatcher("errores.jsp").forward(request, response);
	    } else {
	        request.setAttribute("empleados", empleados);
	        request.getRequestDispatcher("resultadoBusqueda.jsp").forward(request, response);
	    }
	}




	private void mostrarSalarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String dni = request.getParameter("dni");

		if (dni != null && !dni.isEmpty()) {
			try {
				int salario = empresaDAO.getSalarioByDni(dni);
				request.setAttribute("salario", salario);
				request.getRequestDispatcher("resultadoSalario.jsp").forward(request, response);

			} catch (DatosNoCorrectosException | SQLException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("errores.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("salario.jsp").forward(request, response);
		}
	}

	private void editarEmpleado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, DatosNoCorrectosException {
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String sexo = request.getParameter("sexo");
		int categoria = Integer.parseInt(request.getParameter("categoria"));
		int anyos = Integer.parseInt(request.getParameter("anyos"));

		Empleado empleado = new Empleado(dni, nombre, sexo.charAt(0), categoria, anyos);
		empresaDAO.updateEmpleado(empleado);

		request.setAttribute("mensaje", "Empleado actualizado correctamente");
		request.getRequestDispatcher("empleados.jsp").forward(request, response);
	}

	private void mostrarEmpleados(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, DatosNoCorrectosException {
		request.setAttribute("empleados", empresaDAO.getEmpleados());
		request.getRequestDispatcher("empleados.jsp").forward(request, response);
	}
}
