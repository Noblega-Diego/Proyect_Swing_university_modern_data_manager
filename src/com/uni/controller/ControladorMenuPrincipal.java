
package com.uni.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.uni.view.MenuPrincipal;
import com.uni.view.PanelCarrera;
import javax.swing.JPanel;
/**
 *
 * @author diego
 */
public class ControladorMenuPrincipal implements ActionListener{
    private MenuPrincipal view;
    
    public ControladorMenuPrincipal(MenuPrincipal view){
        this.view = view;
        asignarControlador();
        PanelCarrera pc = new PanelCarrera();
        cambiarPanelInferior(pc);
    }
    
    private void asignarControlador(){
        this.view.getBt_carrera().addActionListener(this);
        this.view.getBt_profesores().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    public void cambiarPanelInferior(JPanel panel){
        this.view.getSp_inferior().setViewportView(panel);
    }
}