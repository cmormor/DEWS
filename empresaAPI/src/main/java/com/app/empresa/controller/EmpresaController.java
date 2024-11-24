package com.app.empresa.controller;

import com.app.empresa.entity.Empleado;
import com.app.empresa.exception.DatosNoCorrectosException;
import com.app.empresa.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    // Obtener lista de empleados
    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> mostrarEmpleados() throws SQLException {
        List<Empleado> empleados = empresaService.obtenerEmpleados();
		return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    // Buscar empleados con filtros
    @GetMapping("/buscarEmpleado")
    public ResponseEntity<List<Empleado>> buscarEmpleado(
            @RequestParam(value = "dni", required = false) String dni,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "sexo", required = false) String sexo,
            @RequestParam(value = "categoria", required = false) String categoria,
            @RequestParam(value = "anyos", required = false) Integer anyos) {

        try {
            List<Empleado> empleados = empresaService.buscarEmpleados(dni, nombre, sexo, categoria, anyos);
            return new ResponseEntity<>(empleados, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener salario de un empleado por DNI
    @GetMapping("/salario/{dni}")
    public ResponseEntity<Integer> mostrarSalarios(@PathVariable("dni") String dni) throws SQLException {
        try {
            Empleado empleado = empresaService.obtenerEmpleadoPorDni(dni);
            int salario = empresaService.calcularSalario(empleado);
            return new ResponseEntity<>(salario, HttpStatus.OK);
        } catch (DatosNoCorrectosException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener datos de un empleado por DNI
    @GetMapping("/empleado/{dni}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable("dni") String dni) throws SQLException {
        try {
            Empleado empleado = empresaService.obtenerEmpleadoPorDni(dni);
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        } catch (DatosNoCorrectosException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar datos de un empleado
    @PutMapping("/empleado/{dni}")
    public ResponseEntity<Empleado> editarEmpleado(@PathVariable("dni") String dni,
                                                   @RequestParam(value = "nombre", required = false) String nombre,
                                                   @RequestParam(value = "sexo", required = false) String sexo,
                                                   @RequestParam(value = "categoria", required = false) Integer categoria,
                                                   @RequestParam(value = "anyos", required = false) Integer anyos) throws SQLException {

        try {
            Empleado empleadoExistente = empresaService.obtenerEmpleadoPorDni(dni);

            // Actualizar solo los campos no nulos
            if (nombre != null && !nombre.isEmpty()) {
                empleadoExistente.setNombre(nombre);
            }
            if (sexo != null && !sexo.isEmpty()) {
                if (sexo.length() == 1) {
                    empleadoExistente.setSexo(sexo.charAt(0));
                } else {
                    throw new DatosNoCorrectosException("El sexo debe ser un solo carácter.");
                }
            }
            if (categoria != null) {
                empleadoExistente.setCategoria(categoria);
            }
            if (anyos != null) {
                empleadoExistente.setAnyosTrabajados(anyos);
            }

            // Actualizar en la base de datos
            empresaService.actualizarEmpleado(empleadoExistente);
            return new ResponseEntity<>(empleadoExistente, HttpStatus.OK);
        } catch (DatosNoCorrectosException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Crear un nuevo empleado
    @PostMapping("/empleado")
    public ResponseEntity<String> crearEmpleado(@RequestBody Empleado empleado) {
        try {
            empresaService.crearEmpleado(empleado);
            return new ResponseEntity<>("Empleado creado correctamente.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el empleado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un empleado por DNI
    @DeleteMapping("/empleado/{dni}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable String dni) {
        try {
            empresaService.eliminarEmpleado(dni);
            return new ResponseEntity<>("Empleado eliminado correctamente.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el empleado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
