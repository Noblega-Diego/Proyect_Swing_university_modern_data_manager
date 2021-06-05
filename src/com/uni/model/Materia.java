
package com.uni.model;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author diego
 */
public class Materia {
    private String nombre;
    private Profesor profesor;
    private List<Cursado> cursados = new ArrayList<Cursado>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Cursado> getCursados() {
        return cursados;
    }

    public void setCursados(List<Cursado> cursados) {
        this.cursados = cursados;
    }
    
    
    
}
