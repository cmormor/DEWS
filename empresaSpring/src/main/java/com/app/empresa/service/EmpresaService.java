package com.app.empresa.service;

import com.app.empresa.entity.Empleado;
import com.app.empresa.entity.Nomina;
import com.app.empresa.exception.DatosNoCorrectosException;
import com.app.empresa.repository.EmpleadoRespository;
import com.app.empresa.repository.NominaRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpleadoRespository empleadoRepository;

    @Autowired
    private NominaRepository nominaRepository;

    /**
     * Obtener todos los empleados de la base de datos.
     * 
     * @return Lista de empleados
     */
    public List<Empleado> obtenerEmpleados() {
        return empleadoRepository.findAll();
    }

    /**
     * Obtener un empleado por su DNI.
     * 
     * @param dni DNI del empleado
     * @return Empleado correspondiente al DNI
     * @throws DatosNoCorrectosException Si no se encuentra el empleado
     */
    public Empleado obtenerEmpleadoPorDni(String dni) throws DatosNoCorrectosException {
        return empleadoRepository.findById(dni).orElseThrow(() -> new DatosNoCorrectosException("Empleado no encontrado"));
    }

    /**
     * Buscar empleados según parámetros proporcionados.
     * 
     * @param dni       DNI del empleado
     * @param nombre    Nombre del empleado
     * @param sexo      Sexo del empleado
     * @param categoria Categoría del empleado
     * @param anyos     Años trabajados
     * @return Lista de empleados que coinciden con los parámetros
     * @throws DatosNoCorrectosException Si ocurre un error con los datos
     */
    public List<Empleado> buscarEmpleados(String dni, String nombre, String sexo, String categoria, Integer anyos) throws SQLException {
        // Crear el Specification directamente en el servicio
        Specification<Empleado> specification = (Root<Empleado> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            // Iniciar el predicate con una condición siempre verdadera
            Predicate predicate = criteriaBuilder.conjunction();
            
            if (dni != null && !dni.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("dni"), dni));
            }
            if (nombre != null && !nombre.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("nombre"), "%" + nombre + "%"));
            }
            if (sexo != null && !sexo.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("sexo"), sexo));
            }
            if (categoria != null && !categoria.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("categoria"), categoria));
            }
            if (anyos != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("anyosTrabajados"), anyos));
            }

            return predicate;  // Devolver el predicate final
        };

        // Usar el Specification para realizar la búsqueda en el repositorio
        return empleadoRepository.findAll(specification);
    }

    /**
     * Calcular el salario de un empleado en función de su categoría y años trabajados.
     * 
     * @param empleado Empleado al que se le va a calcular el salario
     * @return El salario calculado
     */
    public int calcularSalario(Empleado empleado) {
        // Aquí debes agregar la lógica para calcular el salario
        // Esto es solo un ejemplo, el cálculo debe ajustarse a tus necesidades
        return empleado.getCategoria() * 1000 + empleado.getAnyosTrabajados() * 100;
    }

    /**
     * Actualizar el salario de un empleado.
     * 
     * @param empleado Empleado cuya nómina será actualizada
     * @throws DatosNoCorrectosException Si hay algún error con los datos
     */
    @Transactional
    public void actualizarSalario(Empleado empleado) throws DatosNoCorrectosException {
        // Lógica para calcular el salario
        int nuevoSalario = calcularSalario(empleado);

        // Buscar la nómina del empleado
        Nomina nomina = nominaRepository.findById(empleado.getDni()).orElseThrow(() -> new DatosNoCorrectosException("Nómina no encontrada"));

        // Actualizar el salario en la entidad Nomina
        nomina.setSueldo(nuevoSalario);

        // Guardar la nómina con el nuevo salario
        nominaRepository.save(nomina);
    }

    /**
     * Actualizar los datos de un empleado.
     * 
     * @param empleado Empleado con los datos a actualizar
     * @throws DatosNoCorrectosException Si los datos no son válidos
     */
    public void actualizarEmpleado(Empleado empleado) throws DatosNoCorrectosException {
        if (empleado.getDni() == null || empleado.getDni().isEmpty()) {
            throw new DatosNoCorrectosException("El DNI del empleado no puede estar vacío.");
        }

        // Verificar si el empleado existe en la base de datos
        empleadoRepository.findById(empleado.getDni()).orElseThrow(() -> new DatosNoCorrectosException("Empleado no encontrado"));

        // Actualizar los datos del empleado
        empleadoRepository.save(empleado);
    }
}
