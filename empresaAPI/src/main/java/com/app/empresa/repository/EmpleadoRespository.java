package com.app.empresa.repository;

import com.app.empresa.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmpleadoRespository extends JpaRepository<Empleado, String>, JpaSpecificationExecutor<Empleado> {
    // Otros métodos personalizados
}
