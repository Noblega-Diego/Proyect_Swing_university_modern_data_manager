package com.uni.model;

import com.uni.dao.DaoCursado;
import java.util.List;

/**
 *
 * @author diego
 */
public class Cursado {
    private Alumno alumno;
    private Materia materia;
    private int nota;

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
     //CRUD
    
//    public static List<Cursado> listarCursado(){
//        return DaoCursado.read();
//    }
    
    public static void updateCursado(Cursado cursado){
        DaoCursado.update(cursado);
    }
    
    public static void createCursado(Cursado cursado){
        DaoCursado.agregar(cursado);
    }
    
    public static void delateCursado(int codigoMateria, int dni){
        DaoCursado.delete(codigoMateria, dni);
    }

    //CRUD FIND
    public static List<Cursado> seleccionarCursados(int dni){
        return DaoCursado.readFilAlumno(dni);
    }
    
    
    
}
