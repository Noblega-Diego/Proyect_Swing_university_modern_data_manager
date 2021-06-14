package com.uni.controller;


import com.uni.model.Materia;
import com.uni.model.Profesor;
import com.uni.view.submenus.materia.PanelMateriaAgregacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author diego
 */
public class ControladorAgregarMateria implements ActionListener{

    private PanelMateriaAgregacion view;
    private ControladorEspacioMateria menuMateria;
    
    public ControladorAgregarMateria(ControladorEspacioMateria menuMateria, PanelMateriaAgregacion view) {
        this.view = view;
        this.menuMateria = menuMateria;
        asignarControlador();
    }

    public void setView(PanelMateriaAgregacion view) {
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
    
    public void cargarProfesores(){
        List<Profesor> profesores = Profesor.listarProfesores();
        Profesor[] profesoresDni = new Profesor[profesores.size()];
        for (int i = 0; i < profesoresDni.length; i++) {
            profesoresDni[i] = profesores.get(i);
        }
        ComboBoxModel boxModel = new DefaultComboBoxModel(profesoresDni);
        this.view.getCb_profesor().setModel(boxModel);
    }
    //CRUD
    
    private void actualizar() {
        Materia materia = new Materia();
        materia.setNombre(view.getTxt_nombre().getText());
        materia.setCodigo(Integer.valueOf(view.getTxt_dni().getText()));
        materia.setProfesor((Profesor) this.view.getCb_profesor().getSelectedItem());
        
        materia.createMateria(materia);
        menuMateria.recargarPlanilla();
        materia = null;
    }
}
