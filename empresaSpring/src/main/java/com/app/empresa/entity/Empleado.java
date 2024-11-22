package com.app.empresa.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Empleados")
public class Empleado implements Serializable {

	@Id
    @Column(name = "dni", nullable = false, length = 9)
    private String dni;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "sexo", nullable = false, length = 1)
    private char sexo;

    @Column(name = "categoria", nullable = false)
    private int categoria;

    @Column(name = "anyos_trabajados", nullable = false)
    private int anyosTrabajados;

    public Empleado() {}

    public Empleado(String dni, String nombre, char sexo, int categoria, int anyosTrabajados) {
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.categoria = categoria;
        this.anyosTrabajados = anyosTrabajados;
    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getAnyosTrabajados() {
        return anyosTrabajados;
    }

    public void setAnyosTrabajados(int anyosTrabajados) {
        this.anyosTrabajados = anyosTrabajados;
    }
}