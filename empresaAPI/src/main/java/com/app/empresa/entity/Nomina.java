package com.app.empresa.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Nominas")
public class Nomina implements Serializable {

    @Id
    @Column(name = "dni", nullable = false, length = 9)

	private String dni;

    @Column(name = "sueldo", nullable = false)
    private int sueldo;

    @OneToOne
    @JoinColumn(name = "dni", referencedColumnName = "dni", insertable = false, updatable = false)
    private Empleado empleado;

    public Nomina() {}

    public Nomina(String dni, int sueldo) {
        this.dni = dni;
        this.sueldo = sueldo;
    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
