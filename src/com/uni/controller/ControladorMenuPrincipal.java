 
package com.uni.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.uni.view.MenuPrincipal;
import com.uni.view.PanelCarrera;
import com.uni.view.PanelMateria;
import com.uni.view.PanelProfesor;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 *
 * @author diego
 */
public class ControladorMenuPrincipal implements ActionListener{
    private MenuPrincipal view;
    
    
    //Controladores
    private ControladorEspacioCarrera controlCarrera;
    private ControladorEspacioProfesor controlProfesor;
    private ControladorEspacioMateria controlMateria;
    
    
    public ControladorMenuPrincipal(MenuPrincipal view){
        this.view = view;
        asignarControlador();
        mostrarCarrera();
    }
    
    private void asignarControlador(){
        this.view.getBt_carrera().addActionListener(this);
        this.view.getBt_profesores().addActionListener(this);
        this.view.getBt_materias().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.view.getBt_carrera())){
            mostrarCarrera();
        }else if(e.getSource().equals(this.view.getBt_profesores())){
            mostrarProfesores();
        }else if(e.getSource().equals(this.view.getBt_materias())){
            mostrarMaterias();
        }
    }
    
    public void cambiarPanelInferior(JPanel panel){
        this.view.getSp_inferior().setViewportView(panel);
    }
    
    private void seleccionarButton(JButton button){
        view.getBt_carrera().setSelected( view.getBt_carrera().equals(button) );
        view.getBt_profesores().setSelected( view.getBt_profesores().equals(button) );
        view.getBt_materias().setSelected( view.getBt_materias().equals(button) );
    }
    
    private void mostrarCarrera(){
        seleccionarButton( this.view.getBt_carrera() );
        PanelCarrera viewCarrera = new PanelCarrera();
        this.controlCarrera = new ControladorEspacioCarrera(viewCarrera);
        cambiarPanelInferior(viewCarrera);
        this.controlCarrera.cargarPlanilla();
    }
    
    private void mostrarProfesores(){
        seleccionarButton( this.view.getBt_profesores() );
        PanelProfesor panelProfesor = new PanelProfesor();
        this.controlProfesor = new ControladorEspacioProfesor(panelProfesor);
        cambiarPanelInferior(panelProfesor);
        this.controlProfesor.cargarPlanilla();
    }    
    
    private void mostrarMaterias(){
        seleccionarButton( this.view.getBt_materias());
        PanelMateria panelMateria = new PanelMateria();
        this.controlMateria = new ControladorEspacioMateria(panelMateria);
        cambiarPanelInferior(panelMateria);
        this.controlMateria.cargarPlanilla();
    }

}