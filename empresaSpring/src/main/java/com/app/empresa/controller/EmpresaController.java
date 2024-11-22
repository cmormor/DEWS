package com.app.empresa.controller;

import com.app.empresa.entity.Empleado;
import com.app.empresa.exception.DatosNoCorrectosException;
import com.app.empresa.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;

	// Página inicial - Redirige al formulario de búsqueda de empleados
	@GetMapping("/")
	public String index() {
		return "index"; // Página principal
	}

	// Página de búsqueda de empleados
	@GetMapping("/buscarEmpleado")
	public String buscarEmpleadoForm() {
		return "buscarEmpleado"; // Página de búsqueda de empleados
	}

	// Procesar búsqueda de empleados
	@PostMapping("/controlador")
	public String buscarEmpleado(@RequestParam(value = "dni", required = false) String dni,
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "sexo", required = false) String sexo,
			@RequestParam(value = "categoria", required = false) String categoria,
			@RequestParam(value = "anyos", required = false) Integer anyos, Model model) {

		try {
			// Buscar empleados según los parámetros proporcionados
			List<Empleado> empleados = empresaService.buscarEmpleados(dni, nombre, sexo, categoria, anyos);

			// Si la lista de empleados es vacía o null, se muestra un mensaje de error
			if (empleados == null || empleados.isEmpty()) {
				model.addAttribute("errorMensaje", "No se encontraron empleados con los criterios de búsqueda.");
				return "errores"; // Página de error
			} else {
				model.addAttribute("empleados", empleados);
				return "resultadosBusqueda"; // Página con los resultados de búsqueda
			}
		} catch (SQLException e) {
			model.addAttribute("errorMensaje", "Ocurrió un error al procesar la solicitud.");
			return "errores"; // Página de error
		}
	}

	// Mostrar salario de un empleado
	@GetMapping("/salario")
	public String mostrarSalariosForm() {
		return "salario"; // Página para ingresar el DNI y buscar el salario
	}

	@PostMapping("/salario")
	public String mostrarSalarios(@RequestParam("dni") String dni, Model model) {
		try {
			Empleado empleado = empresaService.obtenerEmpleadoPorDni(dni);
			int salario = empresaService.calcularSalario(empleado);
			model.addAttribute("salario", salario);
			return "resultadoSalario"; // Página con el salario del empleado
		} catch (DatosNoCorrectosException e) {
			model.addAttribute("error", e.getMessage());
			return "errores"; // Página de error
		}
	}

	// Editar empleado - GET: mostrar el formulario de edición
	@GetMapping("/editarEmpleado")
	public String editarEmpleadoForm(@RequestParam("dni") String dni, Model model) throws SQLException {
		try {
			// Obtener empleado por DNI
			Empleado empleado = empresaService.obtenerEmpleadoPorDni(dni);
			model.addAttribute("empleado", empleado);
			return "editarEmpleado"; // Página de edición de empleado
		} catch (DatosNoCorrectosException e) {
			model.addAttribute("error", e.getMessage());
			return "errores"; // Página de error
		}
	}

	// Editar empleado - POST: procesar la actualización de los datos
	@PostMapping("/editarEmpleado")
	public String editarEmpleado(@RequestParam("dni") String dni,
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "sexo", required = false) String sexo,
			@RequestParam(value = "categoria", required = false) Integer categoria,
			@RequestParam(value = "anyos", required = false) Integer anyos, Model model) throws SQLException {

		try {
			// Obtener el empleado existente por DNI
			Empleado empleadoExistente = empresaService.obtenerEmpleadoPorDni(dni);

			// Actualizar solo si los campos no son nulos o vacíos
			if (nombre != null && !nombre.isEmpty()) {
				empleadoExistente.setNombre(nombre); // Actualizar nombre si no está vacío
			}

			if (sexo != null && !sexo.isEmpty()) {
				// Validar que sexo contenga un solo carácter
				if (sexo.length() == 1) {
					empleadoExistente.setSexo(sexo.charAt(0)); // Convertir el primer carácter en sexo
				} else {
					throw new DatosNoCorrectosException("El sexo debe ser un solo carácter.");
				}
			}

			if (categoria != null) {
				empleadoExistente.setCategoria(categoria); // Actualizar categoría si no es nulo
			}

			if (anyos != null) {
				empleadoExistente.setAnyosTrabajados(anyos); // Actualizar años trabajados si no es nulo
			}

			// Actualizar el empleado en la base de datos
			empresaService.actualizarEmpleado(empleadoExistente);

			// Mensaje de éxito
			model.addAttribute("mensaje", "Empleado actualizado correctamente.");

			// Redirigir a la lista de empleados
			return "redirect:/mostrarEmpleados"; // Redirige a la página de lista de empleados
		} catch (DatosNoCorrectosException e) {
			model.addAttribute("error", "No se pudo actualizar al empleado: " + e.getMessage());
			return "errores"; // Página de error
		} catch (Exception e) {
			model.addAttribute("error", "Ocurrió un error inesperado: " + e.getMessage());
			return "errores"; // Página de error
		}
	}

	// Mostrar lista de empleados
	@GetMapping("/mostrarEmpleados")
	public String mostrarEmpleados(Model model) throws SQLException {
		List<Empleado> empleados = empresaService.obtenerEmpleados();
		model.addAttribute("empleados", empleados);
		return "empleados"; // Página con la lista de empleados
	}

}
