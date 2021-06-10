
package com.uni.controller;
import com.uni.model.Carrera;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import com.uni.view.PanelCarrera;
import com.uni.view.submenus.PanelCarreraEdicion;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author diego
 */
public class ControladorEspacioCarrera implements ActionListener, MouseListener{
    
    private Carrera carrera = new Carrera();
    
    private PanelCarrera panelCarrera;
    private DefaultTableModel modeloTabla;
    
    //Controladores
    private ControladorEdicionCarrera EdicionCarrera;
    
    public ControladorEspacioCarrera(PanelCarrera panelCarrera){
        this.panelCarrera = panelCarrera;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.panelCarrera.getBt_edit().addActionListener(this);
        this.panelCarrera.getBt_list().addActionListener(this);
        this.panelCarrera.getTable_carrera().addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals( this.panelCarrera.getBt_list() )){
            lista();
        }else if(e.getSource().equals( this.panelCarrera.getBt_edit())){
            editar();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int i;
        if(e.getSource().equals(this.panelCarrera.getTable_carrera())){
            i = this.panelCarrera.getTable_carrera().rowAtPoint(e.getPoint());
            Carrera carrera = new Carrera((Integer)this.panelCarrera.getTable_carrera().getValueAt(i, 0),
                    (String)this.panelCarrera.getTable_carrera().getValueAt(i, 1),
                    (String)this.panelCarrera.getTable_carrera().getValueAt(i, 2));
            if(EdicionCarrera != null)
                EdicionCarrera.ingresarCarreraAEditar(carrera);
        }
    }
            
    public void cambiarSubPanel(JPanel panel){
        this.panelCarrera.insertarSubMenu(panel);
    }
    
    public void lista(){
        seleccionarButton(this.panelCarrera.getBt_list());
        cambiarSubPanel(new JPanel());
    }
    

    public void editar(){
        PanelCarreraEdicion panelEdicion = new PanelCarreraEdicion();
        this.EdicionCarrera = new ControladorEdicionCarrera(panelEdicion);
        seleccionarButton(this.panelCarrera.getBt_edit());
        cambiarSubPanel(panelEdicion);
    }
    
    private void seleccionarButton(JButton button){
        panelCarrera.getBt_list().setSelected( button.equals(panelCarrera.getBt_list()) );
        panelCarrera.getBt_edit().setSelected( button.equals(panelCarrera.getBt_edit()) );
    }
    
    
    //CRUD
    
    //Visualiza la tabla
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

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
