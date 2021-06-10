package com.uni.controller;

import com.uni.model.Carrera;
import com.uni.view.submenus.PanelCarreraEdicion;
import com.uni.view.submenus.PanelCarreraEliminacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author diego
 */
public class ControladorEliminacionCarrera implements ActionListener{

    private PanelCarreraEliminacion view;
    private Carrera carrera = null;
    
    public ControladorEliminacionCarrera(PanelCarreraEliminacion view) {
        this.view = view;
        asignarControlador();
    }

    public void setView(PanelCarreraEliminacion view) {
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
    
    public void ingresarCarreraAEliminar(Carrera carrera){
        this.carrera = carrera;
        view.getLb_codigoCarrera().setText(String.valueOf(carrera.getCodigo()));
        view.getLb_nombre().setText(carrera.getNombre());
        view.getLb_duracionCarrera().setText(carrera.getDuracion());
    }
    
    //CRUD
    
    private void eliminar() {
        if(carrera != null){
            carrera.delateCarrera(this.carrera.getCodigo());
        }
        carrera = null;
        view.getLb_codigoCarrera().setText("");
        view.getLb_nombre().setText("");
        view.getLb_duracionCarrera().setText("");
    }
}
