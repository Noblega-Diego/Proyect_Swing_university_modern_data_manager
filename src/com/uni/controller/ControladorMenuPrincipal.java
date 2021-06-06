
package com.uni.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.uni.view.MenuPrincipal;
/**
 *
 * @author diego
 */
public class ControladorMenuPrincipal implements ActionListener{
    private MenuPrincipal view;
    public ControladorMenuPrincipal(MenuPrincipal view){
        this.view = view;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.view.getBt_carrera().addActionListener(this);
        this.view.getBt_profesores().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}