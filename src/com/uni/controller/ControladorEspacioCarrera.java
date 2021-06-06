
package com.uni.controller;
import com.uni.model.Carrera;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.uni.view.PanelCarrera;
/**
 *
 * @author diego
 */
public class ControladorEspacioCarrera implements ActionListener {
    
    private Carrera carrera = new Carrera();
    
    private PanelCarrera panelCarrera;
    
    public ControladorEspacioCarrera(PanelCarrera panelCarrera){
        this.panelCarrera = panelCarrera;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    public void cargarPlanilla(){
        
    }
}
