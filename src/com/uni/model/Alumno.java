
package com.uni.model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author diego
 */
public class Alumno {
    private int dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String domicilio;
    private String telefono;
    private int codigoInscripcion;
    private List<Cursado> cursados = new ArrayList<Cursado>();

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCodigoInscripcion() {
        return codigoInscripcion;
    }

    public void setCodigoInscripcion(int codigoInscripcion) {
        this.codigoInscripcion = codigoInscripcion;
    }

    public List<Cursado> getCursados() {
        return cursados;
    }

    public void setCursados(List<Cursado> cursados) {
        this.cursados = cursados;
    }
    
    
}
