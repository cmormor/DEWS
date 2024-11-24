package com.app.empresa.repository;

import com.app.empresa.entity.Nomina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NominaRepository extends JpaRepository<Nomina, String> {

    @Modifying
    @Transactional
    @Query("UPDATE Nomina n SET n.sueldo = :nuevoSalario WHERE n.dni = :dni")
    void actualizarSalario(String dni, int nuevoSalario);
}
