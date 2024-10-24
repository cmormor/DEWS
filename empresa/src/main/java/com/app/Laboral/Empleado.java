package com.app.Laboral;

import com.app.Exceptions.DatosNoCorrectosException;

/**
 * Clase Empleado que extiende de Persona
 */
public class Empleado extends Persona {

    private int categoria;
    public int anyos;

    public Empleado(String dni, String nombre, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);

        if (nombre == null || nombre.isEmpty() || dni == null || dni.isEmpty() ||
            categoria < 0 || categoria > 10 || anyos < 0) {
            throw new DatosNoCorrectosException("Datos no correctos");
        }

        this.categoria = categoria;
        this.anyos = anyos;
    }

    public Empleado(String dni, String nombre, char sexo) throws DatosNoCorrectosException {
        this(dni, nombre, sexo, 1, 0);
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getAnyos() {
        return anyos;
    }

    public void setAnyos(int anyos) {
        this.anyos = anyos;
    }

    public String Imprime() {
        return "Empleado: Nombre = " + getNombre() + ", DNI = " + getDni() +
               ", Sexo = " + getSexo() + ", Años trabajados = " + anyos + 
               ", Categoria = " + getCategoria();
    }
}
