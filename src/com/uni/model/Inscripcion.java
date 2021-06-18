
package com.uni.model;

import com.uni.dao.DaoInscripcion;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author diego
 */
public class Inscripcion {
    private int codigo;
    private String nombre;
    private LocalDate fechaInscripcion;
    private Carrera carrera;
    private List<Alumno> alumnos;
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    
    public static List<Inscripcion> listarInscripcion(){
        return DaoInscripcion.read();
    }
    
    public static void updateInscipcion(Inscripcion inscripcion){
        DaoInscripcion.update(inscripcion);
    }
    
    public static void createInscripcion(Inscripcion inscripcion){
        DaoInscripcion.agregar(inscripcion);
    }
    
    public static void delateInscripcion(int codigo){
        DaoInscripcion.delete(codigo);
    }

    //CRUD FIND
    public static Inscripcion seleccionarInscripcion(int codigo){
        return DaoInscripcion.select(codigo);
    }
    
    
}
