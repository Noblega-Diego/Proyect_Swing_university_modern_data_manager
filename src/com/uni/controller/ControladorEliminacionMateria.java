package com.uni.controller;


import com.uni.model.Materia;
import com.uni.model.Profesor;
import com.uni.view.submenus.materia.PanelMateriaAgregacion;
import com.uni.view.submenus.materia.PanelMateriaEliminacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author diego
 */
public class ControladorEliminacionMateria implements ActionListener{

    private PanelMateriaEliminacion view;
    private ControladorEspacioMateria menuMateria;
    
    private Materia materia = null;
    
    public ControladorEliminacionMateria(ControladorEspacioMateria menuMateria, PanelMateriaEliminacion view) {
        this.view = view;
        this.menuMateria = menuMateria;
        asignarControlador();
    }

    public void setView(PanelMateriaEliminacion view) {
        this.view = view;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.view.getBt_confirmacionEliminacion().addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.view.getBt_confirmacionEliminacion())){
            actualizar();
        }
    }
    
    public void ingresarMateriaAEditar(Materia materia){
        this.materia = materia;
        view.getLb_NombreMateria().setText(String.valueOf( this.materia.getNombre() ));
        view.getLb_codigo().setText(String.valueOf( this.materia.getCodigo() ));
        view.getLb_NombreDocenteACargo().setText( this.materia.getProfesor().getNombre() );
    }
    
    //CRUD
    
    
    private void actualizar() {
        
        materia.delateMateria(materia.getCodigo());
        menuMateria.recargarPlanilla();
        materia = null;
    }
}
