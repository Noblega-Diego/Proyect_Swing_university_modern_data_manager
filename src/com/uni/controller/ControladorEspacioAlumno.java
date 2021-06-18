
package com.uni.controller;
import com.uni.model.Alumno;
import com.uni.view.PanelAlumno;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import com.uni.view.submenus.alumno.PanelAlumnoVisualizar;
import com.uni.view.submenus.alumno.PanelAlumnoEdicion;
import com.uni.view.submenus.profesor.PanelProfesorEdicion;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author diego
 */
public class ControladorEspacioAlumno implements ActionListener, MouseListener{
    
    private Alumno alumno = new Alumno();
    
    private PanelAlumno panelAlumno;
    private DefaultTableModel modeloTabla;
    
    //Controladores
    private ControladorEdicionAlumno EdicionAlumno;
//    private ControladorAgregarAlumno AgregarAlumno;
//    private ControladorEliminacionAlumno EliminacionAlumno;
    private ControladorVisualizarAlumno VisualizarAlumno;
    
    public ControladorEspacioAlumno(PanelAlumno panelAlumno){
        this.panelAlumno = panelAlumno;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.panelAlumno.getBt_edit().addActionListener(this);
        this.panelAlumno.getBt_list().addActionListener(this);
        this.panelAlumno.getBt_agregar().addActionListener(this);
        this.panelAlumno.getBt_eliminar().addActionListener(this);
        this.panelAlumno.getTable_alumno().addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton){seleccionarButton((JButton) e.getSource());};
        if(e.getSource().equals( this.panelAlumno.getBt_list() )){
            lista();
        }else if(e.getSource().equals( this.panelAlumno.getBt_edit())){
            editar();
        }else if(e.getSource().equals( this.panelAlumno.getBt_agregar())){
//            agregar();
        }else if(e.getSource().equals( this.panelAlumno.getBt_eliminar())){
//            eliminar();
        }
    }
    
    
    //Asignamos materia a editar en los paneles de editar y eliminar
    @Override
    public void mouseClicked(MouseEvent e) {
        int i;
        if(e.getSource().equals(this.panelAlumno.getTable_alumno())){
            i = this.panelAlumno.getTable_alumno().rowAtPoint(e.getPoint());
            Alumno alumno = Alumno.seleccionarAlumno((Integer)this.panelAlumno.getTable_alumno().getValueAt(i, 0));
            if(VisualizarAlumno != null){
                VisualizarAlumno.ingresarAlumnoAEditar(alumno);
            }else if(EdicionAlumno != null){
                EdicionAlumno.ingresarAlumnoAEditar(alumno);
            }
//            else if(EliminacionAlumno != null)
//                EliminacionAlumno.ingresarProfesorAEditar(profesor);
//            }else if(EliminacionAlumno != null)
//                EliminacionAlumno.ingresarProfesorAEditar(profesor);
        } 
    }
            
    public void cambiarSubPanel(JPanel panel){
        this.panelAlumno.insertarSubMenu(panel);
    }
    
    public void lista(){
//        this.AgregarProfesor = null;
//        this.EliminacionAlumno = null
        this.EdicionAlumno = null;

        if(this.VisualizarAlumno == null){
            PanelAlumnoVisualizar panelVisualizacion = new PanelAlumnoVisualizar();
            seleccionarButton(this.panelAlumno.getBt_list());
            this.VisualizarAlumno = new ControladorVisualizarAlumno(this, panelVisualizacion);
            cambiarSubPanel(panelVisualizacion);
        }
    }
    

    public void editar(){
        this.VisualizarAlumno = null;
//        this.AgregarProfesor = null;
//        this.EliminacionProfesor = null;
        
        if(this.EdicionAlumno == null){
            System.gc();
            PanelAlumnoEdicion panelEdicion= new PanelAlumnoEdicion();
            this.EdicionAlumno = new ControladorEdicionAlumno(this, panelEdicion);
            cambiarSubPanel( panelEdicion );
        }
    }
    
    public void agregar(){
//        this.EdicionProfesor = null;
//        this.EliminacionProfesor = null;
//        if(this.AgregarProfesor == null){
//            System.gc();
//            PanelProfesorAgregacion panelAgregacion = new PanelProfesorAgregacion();
//            this.AgregarProfesor = new ControladorAgregarProfesor(this, panelAgregacion);
//            cambiarSubPanel(panelAgregacion);
//        }
    }
    
    public void eliminar(){
//        this.EdicionProfesor = null;
//        this.AgregarProfesor = null;
//        
//        if (this.EliminacionProfesor == null){
//            System.gc();
//            PanelProfesorEliminacion panelElimiacion = new PanelProfesorEliminacion();
//            this.EliminacionProfesor = new ControladorEliminacionProfesor(this, panelElimiacion);
//            cambiarSubPanel(panelElimiacion);
//        }
    }
    
    private void seleccionarButton(JButton button){
        panelAlumno.getBt_list().setSelected( button.equals(panelAlumno.getBt_list()) );
        panelAlumno.getBt_edit().setSelected( button.equals(panelAlumno.getBt_edit()) );
        panelAlumno.getBt_agregar().setSelected( button.equals(panelAlumno.getBt_agregar()) );
        panelAlumno.getBt_eliminar().setSelected( button.equals(panelAlumno.getBt_eliminar()) );
    }
    
    
    //CRUD
    
    //Visualiza la tabla
    public void cargarPlanilla(){
        modeloTabla = (DefaultTableModel) panelAlumno.getTable_alumno().getModel();
        List<Alumno> listaCarreras = Alumno.listarAlumnos();
        Object[] fila = new Object[6];
        for(int i = 0; i< listaCarreras.size(); i++){
              if(listaCarreras.get(i) != null){
                fila[0] = new Integer(listaCarreras.get(i).getDni());
                fila[1] = listaCarreras.get(i).getNombre();
                fila[2] = listaCarreras.get(i).getApellido();
                fila[3] = listaCarreras.get(i).getFechaNacimiento().format(DateTimeFormatter.ISO_LOCAL_DATE);
                fila[4] = listaCarreras.get(i).getDomicilio();
                fila[5] = listaCarreras.get(i).getTelefono();
                modeloTabla.addRow(fila);
            }
        }
        fila = null;
        listaCarreras = null;
        panelAlumno.getTable_alumno().setModel(modeloTabla);
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
