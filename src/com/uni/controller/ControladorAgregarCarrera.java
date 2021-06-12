package com.uni.controller;

import com.uni.model.Carrera;
import com.uni.view.submenus.PanelCarreraAgregacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author diego
 */
public class ControladorAgregarCarrera implements ActionListener{

    private PanelCarreraAgregacion view;
    ControladorEspacioCarrera menuCarrera;
    
    public ControladorAgregarCarrera(ControladorEspacioCarrera menuCarrera, PanelCarreraAgregacion view) {
        this.view = view;
        this.menuCarrera = menuCarrera;
        asignarControlador();
    }

    public void setView(PanelCarreraAgregacion view) {
        this.view = view;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.view.getBt_confirmacion().addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.view.getBt_confirmacion())){
            System.out.println("boton precionado");
            actualizar();
        }
    }
    
    //CRUD
    
    private void actualizar() {
        Carrera carrera = new Carrera();
        carrera.setNombre(view.getTxt_nombre().getText());
        String duracion = view.getTxt_duracion().getText();
        carrera.setCodigo(Integer.valueOf(view.getTxt_codigo().getText()));
        String tipoDuracion = (String)view.getCbx_tipoDuracion().getSelectedItem();
        if(tipoDuracion.contains("Año"))
            duracion += " Años";
        else
            duracion += " Meses";
        carrera.setDuracion(duracion);
        carrera.createCarrera(carrera);
        menuCarrera.recargarPlanilla();
        
    }
}
