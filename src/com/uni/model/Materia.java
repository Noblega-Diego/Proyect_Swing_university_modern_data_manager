
package com.uni.model;
import com.uni.dao.DaoMateria;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author diego
 */
public class Materia {
    private int codigo;
    private String nombre;
    private Profesor profesor;
    private List<Cursado> cursados = new ArrayList<Cursado>();

    public Materia() {
    }

    public Materia(int codigo, String nombre, Profesor profesor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.profesor = profesor;
    }
    
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
    
            
    //CRUD
    
    public List<Materia> listarMaterias(){
        return DaoMateria.read();
    }
    
    public void updateMateria(Materia materia){
        DaoMateria.update(materia);
    }
    
    public void createMateria(Materia materia){
        DaoMateria.agregar(materia);
    }
    
    public void delateMateria(int codigo){
        DaoMateria.delete(codigo);
    }

    
}
