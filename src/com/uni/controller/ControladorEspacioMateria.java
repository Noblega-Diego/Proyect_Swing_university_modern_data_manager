
package com.uni.controller;
import com.uni.model.Materia;
import com.uni.model.Profesor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import com.uni.view.PanelMateria;
import com.uni.view.submenus.materia.PanelMateriaAgregacion;
import com.uni.view.submenus.materia.PanelMateriaEdicion;
import com.uni.view.submenus.materia.PanelMateriaEliminacion;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author diego
 */
public class ControladorEspacioMateria implements ActionListener, MouseListener{
    
    private Materia materia = new Materia();
    
    private PanelMateria panelMateria;
    private DefaultTableModel modeloTabla;
    
    //Controladores
    private ControladorEdicionMateria EdicionMateria;
    private ControladorAgregarMateria AgregarMateria;
    private ControladorEliminacionMateria EliminacionMateria;
    
    public ControladorEspacioMateria(PanelMateria panelMateria){
        this.panelMateria = panelMateria;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.panelMateria.getBt_edit().addActionListener(this);
        this.panelMateria.getBt_list().addActionListener(this);
        this.panelMateria.getBt_agregar().addActionListener(this);
        this.panelMateria.getBt_eliminar().addActionListener(this);
        this.panelMateria.getTable_Materia().addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton){seleccionarButton((JButton) e.getSource());};
        if(e.getSource().equals( this.panelMateria.getBt_list() )){
            lista();
        }else if(e.getSource().equals( this.panelMateria.getBt_edit())){
            editar();
        }else if(e.getSource().equals( this.panelMateria.getBt_agregar())){
            agregar();
        }else if(e.getSource().equals( this.panelMateria.getBt_eliminar())){
            eliminar();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int i;
        if(e.getSource().equals(this.panelMateria.getTable_Materia())){
            i = this.panelMateria.getTable_Materia().rowAtPoint(e.getPoint());
              Profesor profACargo = new Profesor();
              profACargo = Profesor.seleccionarProfesor((Integer)this.panelMateria.getTable_Materia().getValueAt(i, 2));
              Materia materia = new Materia(
                    (Integer)this.panelMateria.getTable_Materia().getValueAt(i, 0),
                    (String)this.panelMateria.getTable_Materia().getValueAt(i, 1),
                    profACargo
                    );
            if(EdicionMateria != null)
                EdicionMateria.ingresarMateriaAEditar(materia);
            else if(EliminacionMateria != null)
                EliminacionMateria.ingresarMateriaAEditar(materia);
        }
    }
            
    public void cambiarSubPanel(JPanel panel){
        this.panelMateria.insertarSubMenu(panel);
    }
    
    public void lista(){
        this.AgregarMateria = null;
        this.EliminacionMateria = null;
        this.EdicionMateria = null;
        
        seleccionarButton(this.panelMateria.getBt_list());
        cambiarSubPanel(null);
    }
    

    public void editar(){
        this.AgregarMateria = null;
        this.EliminacionMateria = null;
        
        if(this.EdicionMateria == null){
            System.gc();
            PanelMateriaEdicion panelEdicion = new PanelMateriaEdicion();
            this.EdicionMateria = new ControladorEdicionMateria(this, panelEdicion);
            cambiarSubPanel(panelEdicion);
            this.EdicionMateria.cargarProfesoresDisponibles();
        }
    }
    
    public void agregar(){
        this.EdicionMateria = null;
        this.EliminacionMateria = null;
        
        if(this.AgregarMateria == null){
            System.gc();
            PanelMateriaAgregacion panelAgregacion = new PanelMateriaAgregacion();
            this.AgregarMateria = new ControladorAgregarMateria(this, panelAgregacion);
            cambiarSubPanel(panelAgregacion);
            this.AgregarMateria.cargarProfesores();
        }
    }
    
    public void eliminar(){
        this.EdicionMateria = null;
        this.AgregarMateria = null;
        
        if (this.EliminacionMateria == null){
            PanelMateriaEliminacion panelElimiacion = new PanelMateriaEliminacion();
            this.EliminacionMateria = new ControladorEliminacionMateria(this, panelElimiacion);
            cambiarSubPanel(panelElimiacion);
        }
    }
    
    private void seleccionarButton(JButton button){
        panelMateria.getBt_list().setSelected( button.equals(panelMateria.getBt_list()) );
        panelMateria.getBt_edit().setSelected( button.equals(panelMateria.getBt_edit()) );
        panelMateria.getBt_agregar().setSelected( button.equals(panelMateria.getBt_agregar()) );
        panelMateria.getBt_eliminar().setSelected( button.equals(panelMateria.getBt_eliminar()) );
    }
    
    
    //CRUD
    
    //Visualiza la tabla
    public void cargarPlanilla(){
        modeloTabla = (DefaultTableModel) panelMateria.getTable_Materia().getModel();
        List<Materia> listaMaterias = materia.listarMaterias();
        Object[] fila = new Object[6];
        for(int i = 0; i< listaMaterias.size(); i++){
              if(listaMaterias.get(i) != null){
                fila[0] = new Integer(listaMaterias.get(i).getCodigo());
                fila[1] = listaMaterias.get(i).getNombre();
                if(listaMaterias.get(i).getProfesor() != null) 
                    fila[2] = listaMaterias.get(i).getProfesor().getDni();
                else 
                    fila[2] = 0;
                
                modeloTabla.addRow(fila);
            }
        }
        fila = null;
        listaMaterias = null;
        panelMateria.getTable_Materia().setModel(modeloTabla);
    }

    public void recargarPlanilla(){
        clearTable();
        cargarPlanilla();
    }
    
    public void clearTable() {
        if(modeloTabla != null)
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                modeloTabla.removeRow(i);
                i -= 1;
            }
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
