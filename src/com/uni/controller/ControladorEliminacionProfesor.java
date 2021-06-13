package com.uni.controller;


import com.uni.model.Profesor;
import com.uni.view.submenus.profesor.PanelProfesorEliminacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 *
 * @author diego
 */
public class ControladorEliminacionProfesor implements ActionListener{

    private PanelProfesorEliminacion view;
    private Profesor profesor = null;
    private ControladorEspacioProfesor menuProfesor;//Menu Principal Profesor
    
    public ControladorEliminacionProfesor(ControladorEspacioProfesor menuProfesor, PanelProfesorEliminacion view) {
        this.view = view;
        this.menuProfesor = menuProfesor;
        asignarControlador();
    }

    public void setView(PanelProfesorEliminacion view) {
        this.view = view;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.view.getBt_confirmacionEliminacion().addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.view.getBt_confirmacionEliminacion())){
            eliminar();
        }
    }
    
    public void ingresarProfesorAEditar(Profesor profesor){
        this.profesor = profesor;
        view.getLb_dni().setText(String.valueOf(profesor.getDni()));
        view.getLb_nombre().setText(profesor.getNombre() +" "+ profesor.getApellido());
        view.getLb_apellido().setText(profesor.getApellido());
        LocalDate fecha = profesor.getFedchaNacimiento();
        view.getLb_fechaNacimiento().setText(
                String.valueOf(fecha.getYear())+ " / " +
                String.valueOf(fecha.getMonth().getValue() -1) +" / "+
                String.valueOf(fecha.getDayOfMonth()));
        view.getLb_domicilio().setText(profesor.getDomicilio());
        view.getLb_telefono().setText(profesor.getTelefono());

    }
    
    //CRUD
    
    private void eliminar() {
        if(profesor != null){
            profesor.delateProfesor(profesor.getDni());
            menuProfesor.recargarPlanilla();
        }
    }
}
