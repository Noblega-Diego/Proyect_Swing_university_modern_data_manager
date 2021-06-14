
package com.uni.model;

import com.uni.dao.DaoProfesor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class Profesor {
    private int dni;
    private String nombre;
    private String apellido;
    private LocalDate fedchaNacimiento;
    private String domicilio;
    private String telefono;
    private List<Materia> materias= new ArrayList<Materia>();

    public Profesor() {
    }
    
    public Profesor(int dni, String nombre, String apellido, LocalDate fedchaNacimiento, String domicilio, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fedchaNacimiento = fedchaNacimiento;
        this.domicilio = domicilio;
        this.telefono = telefono;
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

    public LocalDate getFedchaNacimiento() {
        return fedchaNacimiento;
    }

    public void setFedchaNacimiento(LocalDate fedchaNacimiento) {
        this.fedchaNacimiento = fedchaNacimiento;
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

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
    
    
        
    //CRUD
    
    public static List<Profesor> listarProfesores(){
        return DaoProfesor.read();
    }
    
    public static void updateProfesor(Profesor profesor){
        DaoProfesor.update(profesor);
    }
    
    public static void createProfesor(Profesor profesor){
        DaoProfesor.agregar(profesor);
    }
    
    public static void delateProfesor(int codigo){
        DaoProfesor.delete(codigo);
    }

    //CRUD FIND
    public static Profesor seleccionarProfesor(int dni){
        return DaoProfesor.select(dni);
    }
    
    
    @Override
    public String toString() {
        return "dni: " + dni;
    }
    
}
