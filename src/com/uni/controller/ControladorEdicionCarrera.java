package com.uni.controller;

import com.uni.model.Carrera;
import com.uni.view.submenus.PanelCarreraEdicion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author diego
 */
public class ControladorEdicionCarrera implements ActionListener{

    private PanelCarreraEdicion view;
    private Carrera carrera = null;
    
    public ControladorEdicionCarrera(PanelCarreraEdicion view) {
        this.view = view;
        asignarControlador();
    }

    public void setView(PanelCarreraEdicion view) {
        this.view = view;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.view.getBt_confirmacionEdicion().addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.view.getBt_confirmacionEdicion())){
            System.out.println("boton precionado");
            actualizar();
        }
    }
    
    public void ingresarCarreraAEditar(Carrera carrera){
        this.carrera = carrera;
        view.getLb_codigoCarrera().setText(String.valueOf(carrera.getCodigo()));
        view.getLb_nombre().setText(carrera.getNombre());
        view.getTxt_nombre().setText(carrera.getNombre());
        
        
        view.getTxt_nombre().setText(carrera.getNombre());
        String duracion = carrera.getDuracion();
        String[] duracionSeparada = duracion.split(" ");
        view.getTxt_duracion().setText(duracionSeparada[0]);
        if(duracionSeparada[1].contains("Año"))
           view.getCbx_tipoDuracion().setSelectedIndex(0);
        else
           view.getCbx_tipoDuracion().setSelectedIndex(1);
    }
    
    //CRUD
    
    private void actualizar() {
        if(carrera != null){
            carrera.setNombre(view.getTxt_nombre().getText());
            String duracion = view.getTxt_duracion().getText();
            String tipoDuracion = (String)view.getCbx_tipoDuracion().getSelectedItem();
            if(tipoDuracion.contains("Año"))
                duracion += " Año";
            else
                duracion += " Mes";
            carrera.setDuracion(duracion);
            carrera.updateCarrera(carrera);
            System.out.println("Operacion realisada con exito");
        }
    }
}
