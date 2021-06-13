package com.uni.controller;


import com.uni.model.Profesor;
import com.uni.view.submenus.profesor.PanelProfesorAgregacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;

/**
 *
 * @author diego
 */
public class ControladorAgregarProfesor implements ActionListener{

    private PanelProfesorAgregacion view;
    private ControladorEspacioProfesor menuProfesor;
    
    public ControladorAgregarProfesor(ControladorEspacioProfesor menuProfesor, PanelProfesorAgregacion view) {
        this.view = view;
        this.menuProfesor = menuProfesor;
        asignarControlador();
    }

    public void setView(PanelProfesorAgregacion view) {
        this.view = view;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.view.getBt_confirmacion().addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.view.getBt_confirmacion())){
            actualizar();
        }
    }
    
    //CRUD
    
    private void actualizar() {
        Profesor profesor = new Profesor();
        profesor.setNombre(view.getTxt_nombre().getText());
        profesor.setApellido(view.getTxt_apellido().getText());
        Calendar fecha = view.getDc_FechaNacimiento().getCalendar();
        profesor.setFedchaNacimiento(LocalDate.of(
                fecha.get(Calendar.YEAR),  //Año
                fecha.get(Calendar.MONTH)+1,  //Mes
                fecha.get(Calendar.DAY_OF_MONTH)));//dia
        profesor.setDomicilio(view.getTxt_domicilio().getText());
        profesor.setTelefono(view.getTxt_telefono().getText());
        profesor.setDni(Integer.valueOf(view.getTxt_telefono().getText()));
        
        
        profesor.createProfesor(profesor);
        menuProfesor.recargarPlanilla();
        profesor = null;
    }
}
