
package com.uni.controller;
import com.uni.model.Carrera;
import com.uni.model.Profesor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import com.uni.view.PanelProfesor;
import com.uni.view.submenus.profesor.PanelProfesorEdicion;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author diego
 */
public class ControladorEspacioProfesor implements ActionListener, MouseListener{
    
    private Profesor profesor = new Profesor();
    
    private PanelProfesor panelProfesor;
    private DefaultTableModel modeloTabla;
    
    //Controladores
    private ControladorEdicionProfesor EdicionProfesor;
//    private ControladorAgregarProfesor AgregarProfesor;
//    private ControladorEliminacionProfesor EliminacionProfesor;
    
    public ControladorEspacioProfesor(PanelProfesor panelCarrera){
        this.panelProfesor = panelCarrera;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.panelProfesor.getBt_edit().addActionListener(this);
        this.panelProfesor.getBt_list().addActionListener(this);
        this.panelProfesor.getBt_agregar().addActionListener(this);
        this.panelProfesor.getBt_eliminar().addActionListener(this);
        this.panelProfesor.getTable_profesor().addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton){seleccionarButton((JButton) e.getSource());};
        if(e.getSource().equals( this.panelProfesor.getBt_list() )){
            lista();
        }else if(e.getSource().equals( this.panelProfesor.getBt_edit())){
            editar();
        }else if(e.getSource().equals( this.panelProfesor.getBt_agregar())){
            agregar();
        }else if(e.getSource().equals( this.panelProfesor.getBt_eliminar())){
            eliminar();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int i;
        if(e.getSource().equals(this.panelProfesor.getTable_profesor())){
            i = this.panelProfesor.getTable_profesor().rowAtPoint(e.getPoint());
            String[] fecha = ((String)this.panelProfesor.getTable_profesor().getValueAt(i, 3)).split("-");
            Profesor profesor = new Profesor(
                    (Integer)this.panelProfesor.getTable_profesor().getValueAt(i, 0),
                    (String)this.panelProfesor.getTable_profesor().getValueAt(i, 1),
                    (String)this.panelProfesor.getTable_profesor().getValueAt(i, 2),
                    LocalDate.of(Integer.valueOf(fecha[0]), Integer.valueOf(fecha[1]), Integer.valueOf(fecha[2])),
                    (String)this.panelProfesor.getTable_profesor().getValueAt(i, 4),
                    (String)this.panelProfesor.getTable_profesor().getValueAt(i, 5)
                    );
            if(EdicionProfesor != null)
                EdicionProfesor.ingresarProfesorAEditar(profesor);
//            else if(EliminacionProfesor != null)
//                EliminacionProfesor.ingresarCarreraAEliminar(carrera);
        }
    }
            
    public void cambiarSubPanel(JPanel panel){
        this.panelProfesor.insertarSubMenu(panel);
    }
    
    public void lista(){
        seleccionarButton(this.panelProfesor.getBt_list());
        cambiarSubPanel(new JPanel());
    }
    

    public void editar(){
//        this.AgregarProfesor = null;
//        this.EliminacionProfesor = null;
        
        PanelProfesorEdicion panelEdicion = new PanelProfesorEdicion();
        this.EdicionProfesor = new ControladorEdicionProfesor(this, panelEdicion);
        cambiarSubPanel(panelEdicion);
    }
    
    public void agregar(){
//        this.EdicionProfesor = null;
//        this.EliminacionProfessor = null;
//        
//        PanelCarreraAgregacion panelAgregacion = new PanelCarreraAgregacion();
//        this.AgregarProfesor = new ControladorAgregarProfesor(this, panelAgregacion);
//        cambiarSubPanel(panelAgregacion);
    }
    
    public void eliminar(){
//        this.EdicionProfesor = null;
//        this.AgregarProfesor = null;
//        
//        PanelCarreraEliminacion panelElimiacion = new PanelCarreraEliminacion();
//        this.EliminacionProfesor = new ControladorEliminacionProfesor(this, panelElimiacion);
//        cambiarSubPanel(panelElimiacion);
    }
    
    private void seleccionarButton(JButton button){
        panelProfesor.getBt_list().setSelected( button.equals(panelProfesor.getBt_list()) );
        panelProfesor.getBt_edit().setSelected( button.equals(panelProfesor.getBt_edit()) );
        panelProfesor.getBt_agregar().setSelected( button.equals(panelProfesor.getBt_agregar()) );
        panelProfesor.getBt_eliminar().setSelected( button.equals(panelProfesor.getBt_eliminar()) );
    }
    
    
    //CRUD
    
    //Visualiza la tabla
    public void cargarPlanilla(){
        modeloTabla = (DefaultTableModel) panelProfesor.getTable_profesor().getModel();
        List<Profesor> listaCarreras = profesor.listarProfesores();
        Object[] fila = new Object[6];
        for(int i = 0; i< listaCarreras.size(); i++){
              if(listaCarreras.get(i) != null){
                fila[0] = new Integer(listaCarreras.get(i).getDni());
                fila[1] = listaCarreras.get(i).getNombre();
                fila[2] = listaCarreras.get(i).getApellido();
                fila[3] = listaCarreras.get(i).getFedchaNacimiento().format(DateTimeFormatter.ISO_LOCAL_DATE);
                fila[4] = listaCarreras.get(i).getDomicilio();
                fila[5] = listaCarreras.get(i).getTelefono();
                modeloTabla.addRow(fila);
            }
        }
        fila = null;
        listaCarreras = null;
        panelProfesor.getTable_profesor().setModel(modeloTabla);
    }

    public void recargarPlanilla(){
//        modeloTabla = (DefaultTableModel) panelCarrera.getTable_carrera().getModel();
//        List<Carrera> listaCarreras = carrera.listarCarreras();
//        Object[] fila = new Object[3];
//        for(int i = 0; i< listaCarreras.size(); i++){
//              if(listaCarreras.get(i) != null){
//                fila[0] = new Integer(listaCarreras.get(i).getCodigo());
//                fila[1] = listaCarreras.get(i).getNombre();
//                fila[2] = listaCarreras.get(i).getDuracion();
//                modeloTabla.addRow(fila);
//            }
//        }
//        listaCarreras = null;
//        panelCarrera.getTable_carrera().setModel(modeloTabla);
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
