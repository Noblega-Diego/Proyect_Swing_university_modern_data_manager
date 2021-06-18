
package com.uni.model;

import com.uni.dao.DaoAlumno;
import com.uni.dao.DaoInscripcion;
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
    private Inscripcion inscripcion;
    private List<Cursado> cursados = new ArrayList<Cursado>();

    public Alumno() {
    }
    
    public Alumno(int dni, String nombre, String apellido, LocalDate fechaNacimiento, String domicilio, String telefono, Inscripcion inscripcion) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.inscripcion = inscripcion;
    }
    
    
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

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }
    

    public List<Cursado> getCursados() {
        return cursados;
    }

    public void setCursados(List<Cursado> cursados) {
        this.cursados = cursados;
    }
    
    
    public static List<Alumno> listarAlumnos(){
        return DaoAlumno.read();
    }
    
    public static void updateAlumno(Alumno alumno){
        DaoAlumno.update(alumno);
    }
    
    public static void createAlumno(Alumno alumno){
        DaoAlumno.agregar(alumno);
    }
    
    public static void delateAlumno(int dni){
        DaoAlumno.delete(dni);
    }

    //CRUD FIND
    public static Alumno seleccionarAlumno(int dni){
        return DaoAlumno.select(dni);
    }
    
}
