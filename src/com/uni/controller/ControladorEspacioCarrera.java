
package com.uni.controller;
import com.uni.model.Carrera;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.uni.view.PanelCarrera;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author diego
 */
public class ControladorEspacioCarrera implements ActionListener {
    
    private Carrera carrera = new Carrera();
    
    private PanelCarrera panelCarrera;
    private DefaultTableModel modeloTabla;
    
    public ControladorEspacioCarrera(PanelCarrera panelCarrera){
        this.panelCarrera = panelCarrera;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    public void cargarPlanilla(){
        modeloTabla = (DefaultTableModel) panelCarrera.getTable_carrera().getModel();
        List<Carrera> listaCarreras = carrera.listarCarreras();
        Object[] fila = new Object[3];
        for(int i = 0; i< listaCarreras.size(); i++){
              if(listaCarreras.get(i) != null){
                fila[0] = new Integer(listaCarreras.get(i).getCodigo());
                fila[1] = listaCarreras.get(i).getNombre();
                fila[2] = listaCarreras.get(i).getDuracion();
                modeloTabla.addRow(fila);
            }
        }
        listaCarreras = null;
        panelCarrera.getTable_carrera().setModel(modeloTabla);
    }
}
