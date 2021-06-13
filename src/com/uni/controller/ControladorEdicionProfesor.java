package com.uni.controller;


import com.uni.model.Profesor;
import com.uni.view.submenus.profesor.PanelProfesorEdicion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author diego
 */
public class ControladorEdicionProfesor implements ActionListener{

    private PanelProfesorEdicion view;
    private Profesor profesor = null;
    private ControladorEspacioProfesor menuProfesor;
    
    public ControladorEdicionProfesor(ControladorEspacioProfesor menuProfesor, PanelProfesorEdicion view) {
        this.view = view;
        this.menuProfesor = menuProfesor;
        asignarControlador();
    }

    public void setView(PanelProfesorEdicion view) {
        this.view = view;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.view.getBt_confirmacionEdicion().addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.view.getBt_confirmacionEdicion())){
            actualizar();
        }
    }
    
    public void ingresarProfesorAEditar(Profesor profesor){
        this.profesor = profesor;
        view.getLb_dni().setText(String.valueOf(profesor.getDni()));
        view.getLb_nombre().setText(profesor.getNombre() +" "+ profesor.getApellido());
        view.getTxt_nombre().setText(profesor.getNombre());
        view.getTxt_apellido().setText(profesor.getApellido());
        LocalDate fecha = profesor.getFedchaNacimiento();
        view.getDc_FechaNacimiento().setDate(new Date(fecha.getYear() - 1900, fecha.getMonth().getValue() -1, fecha.getDayOfMonth()));
        view.getTxt_domicilio().setText(profesor.getDomicilio());
        view.getTxt_telefono().setText(profesor.getTelefono());

    }
    
    //CRUD
    
    private void actualizar() {
        if(profesor != null){
            profesor.setNombre(view.getTxt_nombre().getText());
            profesor.setApellido(view.getTxt_apellido().getText());
            Calendar fecha = view.getDc_FechaNacimiento().getCalendar();
            profesor.setFedchaNacimiento(LocalDate.of(
                    fecha.get(Calendar.YEAR),  //Año
                    fecha.get(Calendar.MONTH)+1,  //Mes
                    fecha.get(Calendar.DAY_OF_MONTH)));//dia
            profesor.setDomicilio(view.getTxt_domicilio().getText());
            profesor.setTelefono(view.getTxt_telefono().getText());
            profesor.updateProfesor(profesor);
            menuProfesor.recargarPlanilla();
        }
    }
}
