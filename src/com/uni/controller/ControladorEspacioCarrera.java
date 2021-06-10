
package com.uni.controller;
import com.uni.model.Carrera;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import com.uni.view.PanelCarrera;
import com.uni.view.submenus.PanelCarreraAgregacion;
import com.uni.view.submenus.PanelCarreraEdicion;
import com.uni.view.submenus.PanelCarreraEliminacion;
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
    private ControladorAgregarCarrera AgregarCarrera;
    private ControladorEliminacionCarrera EliminacionCarrera;
    
    public ControladorEspacioCarrera(PanelCarrera panelCarrera){
        this.panelCarrera = panelCarrera;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.panelCarrera.getBt_edit().addActionListener(this);
        this.panelCarrera.getBt_list().addActionListener(this);
        this.panelCarrera.getBt_agregar().addActionListener(this);
        this.panelCarrera.getBt_eliminar().addActionListener(this);
        this.panelCarrera.getTable_carrera().addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals( this.panelCarrera.getBt_list() )){
            lista();
        }else if(e.getSource().equals( this.panelCarrera.getBt_edit())){
            editar();
        }else if(e.getSource().equals( this.panelCarrera.getBt_agregar())){
            agregar();
        }else if(e.getSource().equals( this.panelCarrera.getBt_eliminar())){
            eliminar();
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
            else if(EliminacionCarrera != null)
                EliminacionCarrera.ingresarCarreraAEliminar(carrera);
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
        this.AgregarCarrera = null;
        this.EliminacionCarrera = null;
        
        PanelCarreraEdicion panelEdicion = new PanelCarreraEdicion();
        this.EdicionCarrera = new ControladorEdicionCarrera(panelEdicion);
        seleccionarButton(this.panelCarrera.getBt_edit());
        cambiarSubPanel(panelEdicion);
    }
    
    public void agregar(){
        this.EdicionCarrera = null;
        this.EliminacionCarrera = null;
        
        PanelCarreraAgregacion panelAgregacion = new PanelCarreraAgregacion();
        this.AgregarCarrera = new ControladorAgregarCarrera(panelAgregacion);
        seleccionarButton(this.panelCarrera.getBt_agregar());
        cambiarSubPanel(panelAgregacion);
    }
    
    public void eliminar(){
        this.EdicionCarrera = null;
        this.AgregarCarrera = null;
        
        PanelCarreraEliminacion panelElimiacion = new PanelCarreraEliminacion();
        this.EliminacionCarrera = new ControladorEliminacionCarrera(panelElimiacion);
        seleccionarButton(this.panelCarrera.getBt_eliminar());
        cambiarSubPanel(panelElimiacion);
    }
    
    private void seleccionarButton(JButton button){
        panelCarrera.getBt_list().setSelected( button.equals(panelCarrera.getBt_list()) );
        panelCarrera.getBt_edit().setSelected( button.equals(panelCarrera.getBt_edit()) );
        panelCarrera.getBt_agregar().setSelected( button.equals(panelCarrera.getBt_agregar()) );
        panelCarrera.getBt_eliminar().setSelected( button.equals(panelCarrera.getBt_eliminar()) );
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
