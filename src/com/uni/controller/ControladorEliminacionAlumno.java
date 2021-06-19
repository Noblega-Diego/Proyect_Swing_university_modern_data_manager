package com.uni.controller;


import com.uni.model.Alumno;
import com.uni.model.Cursado;
import com.uni.model.Inscripcion;
import com.uni.model.Materia;
import com.uni.view.submenus.alumno.PanelAlumnoEliminacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class ControladorEliminacionAlumno implements ActionListener{

    private PanelAlumnoEliminacion view;
    private ControladorEspacioAlumno menuAlumno;

    private Alumno alumno = null;
    
    public ControladorEliminacionAlumno(ControladorEspacioAlumno menuAlumno, PanelAlumnoEliminacion view) {
        this.view = view;
        this.menuAlumno = menuAlumno;
        asignarControlador();
    }

    public void setView(PanelAlumnoEliminacion view) {
        this.view = view;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.view.getBt_ConfirmarEliminacion().addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals( this.view.getBt_ConfirmarEliminacion()))
            eliminarAlumno();
    }
    
    public void ingresarAlumnoAEliminar(Alumno alumno){
        this.alumno = alumno;
        view.getTxt_AlumnoNombre().setText(alumno.getNombre());
        view.getTxt_AlumnoApellido().setText(alumno.getApellido());
        view.getTxt_AlumnoDni().setText(String.valueOf(alumno.getDni()));
        view.getTxt_AlumnoResidencia().setText(alumno.getDomicilio());
        view.getTxt_AlumnoTelefono().setText(alumno.getTelefono());
        view.getTxt_AlumnoCarreraNombre().setText(alumno.getInscripcion().getCarrera().getNombre());
        view.getTxt_AlumnoCarreraCodigo().setText(String.valueOf(alumno.getInscripcion().getCarrera().getCodigo()));
        view.getTxt_InscripcionFecha().setText(String.valueOf(alumno.getInscripcion().getFechaInscripcion().format(DateTimeFormatter.ISO_DATE)));
    }

    private void eliminarAlumno() {
        if(alumno != null){
            Cursado.delateCursadoAlumno(alumno.getDni());
            Inscripcion.deleteInscripcion(alumno.getInscripcion().getCodigo());
            Alumno.delateAlumno(alumno.getDni());
            alumno = null;
        }
        this.menuAlumno.recargarPlanilla();
    }
    
}
