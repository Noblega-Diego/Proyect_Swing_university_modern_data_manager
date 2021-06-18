package com.uni.controller;


import com.uni.model.Materia;
import com.uni.model.Profesor;
import com.uni.view.submenus.profesor.PanelProfesorEdicion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author diego
 */
public class ControladorEdicionProfesor implements ActionListener{

    private PanelProfesorEdicion view;
    private Profesor profesor = null;
    private ControladorEspacioProfesor menuProfesor;

    private List<Materia> materiasDejadas = new ArrayList<>();
    
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
        this.view.getBt_transferirMateria().addActionListener(this);
        this.view.getBt_eliminacionMateria().addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.view.getBt_confirmacionEdicion())){
            actualizar();
        }else if(e.getSource().equals(this.view.getBt_eliminacionMateria())){
            eliminarMateria();
        }else if(e.getSource().equals(this.view.getBt_transferirMateria())){
            trasferirMateria();
        }
    }
    
    public void ingresarProfesorAEditar(Profesor profesor){
        this.profesor = profesor;
        view.getLb_dni().setText(String.valueOf(profesor.getDni()));
        view.getLb_nombre().setText(profesor.getNombre() +" "+ profesor.getApellido());
        view.getTxt_nombre().setText(profesor.getNombre());
        view.getTxt_apellido().setText(profesor.getApellido());
        LocalDate fecha = profesor.getFechaNacimiento();
        view.getDc_FechaNacimiento().setDate(new Date(
                fecha.getYear() - 1900, 
                fecha.getMonth().getValue() -1, 
                fecha.getDayOfMonth()));
        view.getTxt_domicilio().setText(profesor.getDomicilio());
        view.getTxt_telefono().setText(profesor.getTelefono());
        
        if(profesor.getMaterias().size() != 0){
            DefaultListModel materiasDictadas = new DefaultListModel<Materia>();
            for (int i = 0; i < profesor.getMaterias().size(); i++) {
                materiasDictadas.addElement(profesor.getMaterias().get(i));
            }
            view.getLst_MateriasDictadas().setModel(materiasDictadas);
            materiasDictadas = null;
        }else{
            view.getLst_MateriasDictadas().setModel(new DefaultListModel());
        }
        
        //Listamos las materias dispobles a asignar al profesor
        List<Materia> materiasDisponibles = Materia.listarMateriaNoSonProfesor(profesor.getDni());
        if(materiasDisponibles.size() != 0){
            DefaultComboBoxModel materias = new DefaultComboBoxModel<Materia>();
            for (int i = 0; i < materiasDisponibles.size(); i++) {
                materias.addElement(materiasDisponibles.get(i));
            }
            view.getCb_MateriasDisponibles().setModel(materias);
            materias = null;
        }else{
            view.getCb_MateriasDisponibles().setModel(new DefaultComboBoxModel());
        }
        
    }
    
    private void trasferirMateria(){
        int indexSelected = this.view.getCb_MateriasDisponibles().getSelectedIndex();
        DefaultComboBoxModel modeloTabla = new DefaultComboBoxModel();
        for(int i = 0; i < this.view.getCb_MateriasDisponibles().getModel().getSize(); i++){
            if(i != indexSelected){
                modeloTabla.addElement(this.view.getCb_MateriasDisponibles().getModel().getElementAt(i));
            }else{
                DefaultListModel modelolistaMaterias = (DefaultListModel) this.view.getLst_MateriasDictadas().getModel();
                modelolistaMaterias.addElement(this.view.getCb_MateriasDisponibles().getModel().getElementAt(i));
                
                this.materiasDejadas.remove(this.view.getCb_MateriasDisponibles().getModel().getElementAt(i));
                this.view.getLst_MateriasDictadas().setModel(modelolistaMaterias);
                modelolistaMaterias = null;
            }
        }
        this.view.getCb_MateriasDisponibles().setModel(modeloTabla);
        modeloTabla = null;
    }
    
    private void eliminarMateria(){
        int indexMateriaDelate = this.view.getLst_MateriasDictadas().getSelectedIndex();
        DefaultListModel modeloMaterias = new DefaultListModel();
        for (int i = 0; i < view.getLst_MateriasDictadas().getModel().getSize(); i++) {
            if(i != indexMateriaDelate){
                modeloMaterias.addElement(this.view.getLst_MateriasDictadas().getModel().getElementAt(i));
            }else{
                //Trasladamos la materia al combBox de materias disponibles
                DefaultComboBoxModel materiasDisonibles = (DefaultComboBoxModel)this.view.getCb_MateriasDisponibles().getModel();
                materiasDisonibles.addElement(this.view.getLst_MateriasDictadas().getModel().getElementAt(i));
                
                //insertamos la tabla con la materia 
                this.view.getCb_MateriasDisponibles().setModel(materiasDisonibles);
                this.materiasDejadas.add((Materia) this.view.getLst_MateriasDictadas().getModel().getElementAt(i));
                materiasDisonibles = null;
            }
        }
        this.view.getLst_MateriasDictadas().setModel(modeloMaterias);
        modeloMaterias = null;
    }
    
    
    //CRUD
    
    private void actualizar() {
        if(profesor != null){
            profesor.setNombre(view.getTxt_nombre().getText());
            profesor.setApellido(view.getTxt_apellido().getText());
            Calendar fecha = view.getDc_FechaNacimiento().getCalendar();
            profesor.setFechaNacimiento(LocalDate.of(
                    fecha.get(Calendar.YEAR),  //Año
                    fecha.get(Calendar.MONTH)+1,  //Mes
                    fecha.get(Calendar.DAY_OF_MONTH)));//dia
            profesor.setDomicilio(view.getTxt_domicilio().getText());
            profesor.setTelefono(view.getTxt_telefono().getText());
            
            
            
            //Acutualizamos la lista de materias del profesor
            List<Materia> materias = new ArrayList<>();
            for (int i = 0; i < view.getLst_MateriasDictadas().getModel().getSize(); i++) {
                materias.add((Materia) this.view.getLst_MateriasDictadas().getModel().getElementAt(i));
            }
            profesor.setMaterias(materias);
            

            //Deshacemos las relaciones con aquellas materias q ya no dictara el profesor
            for (Materia materia : materiasDejadas) {
                materia.setProfesor(null);
                Materia.updateMateria(materia);
            }
            profesor.setMaterias(materias);
            
            //Actualizamos las materias
            for (Materia materia : profesor.getMaterias()) {
                materia.setProfesor(profesor);
                Materia.updateMateria(materia);
            }
            
            //Actualizamos el profesor
            profesor.updateProfesor(profesor);
            menuProfesor.recargarPlanilla();
        }
    }
}
