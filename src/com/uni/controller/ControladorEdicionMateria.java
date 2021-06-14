package com.uni.controller;


import com.uni.model.Materia;
import com.uni.model.Profesor;
import com.uni.view.submenus.materia.PanelMateriaEdicion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author diego
 */
public class ControladorEdicionMateria implements ActionListener, ItemListener{

    private PanelMateriaEdicion view;
    private Materia materia = null;
    private ControladorEspacioMateria menuMateria;
            
    public ControladorEdicionMateria(ControladorEspacioMateria menuMateria, PanelMateriaEdicion view) {
        this.view = view;
        this.menuMateria = menuMateria;
        asignarControlador();
        view.getCb_ProfesorATransferir().setEnabled(false);
    }

    public void setView(PanelMateriaEdicion view) {
        this.view = view;
        asignarControlador();
        view.getCb_ProfesorATransferir().setEnabled(false);

    }
    
    private void asignarControlador(){
        this.view.getBt_confirmacionEdicion().addActionListener(this);
        this.view.getCb_ProfesorATransferir().addItemListener(this);
    }
    
    //Deteccion de eventos
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.view.getBt_confirmacionEdicion())){
            actualizar();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Profesor profesor = (Profesor) e.getItem();
        if(profesor != null){
            view.getTxt_ProfesorATransferirNombre().setText(profesor.getNombre());
            view.getTxt_ProfesorATransferirDni().setText(String.valueOf(profesor.getDni()));
        }else{
            view.getTxt_ProfesorATransferirNombre().setText("");
            view.getTxt_ProfesorATransferirDni().setText("");
        }
    }
    
    
    
    
    //CRUD
    
    public void ingresarMateriaAEditar(Materia materia){
        this.materia = materia;
        view.getLb_MateriaCodigo().setText(String.valueOf( this.materia.getCodigo() ));
        view.getLb_MateriaNombre().setText( this.materia.getNombre() );
        view.getTxt_nombreMateria().setText( this.materia.getNombre() );
        view.getTxt_ProfesorACargoDni().setText(String.valueOf( this.materia.getProfesor().getDni() ));
        view.getTxt_ProfesorACargoNombre().setText( this.materia.getProfesor().getNombre() + " " +this.materia.getProfesor().getApellido());
        
        view.getCb_ProfesorATransferir().setEnabled(true);
        view.getTxt_ProfesorATransferirDni().setText("");
        view.getTxt_ProfesorATransferirNombre().setText("");
    }
    
    public void cargarProfesoresDisponibles(){
        List<Profesor> profesores = Profesor.listarProfesores();
        Profesor[] arrProfesores = new Profesor[profesores.size() + 1];
        for(int i = 0; i< arrProfesores.length -1 && i < profesores.size(); i++){
            arrProfesores[i] = profesores.get(i);
        }
        arrProfesores[arrProfesores.length-1] = null;
        this.view.getCb_ProfesorATransferir().setModel(new DefaultComboBoxModel(arrProfesores));
        profesores = null;
        arrProfesores = null;
    }
    
    private void actualizar() {
        if(materia != null){
            materia.setNombre(view.getTxt_nombreMateria().getText());
            materia.setProfesor((Profesor) view.getCb_ProfesorATransferir().getSelectedItem());
            materia.updateMateria(materia);
            menuMateria.recargarPlanilla();
        }
    }
    
}
