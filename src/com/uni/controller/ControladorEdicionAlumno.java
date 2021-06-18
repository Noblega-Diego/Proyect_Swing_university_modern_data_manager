package com.uni.controller;


import com.uni.model.Alumno;
import com.uni.model.Cursado;
import com.uni.model.Materia;
import com.uni.view.submenus.alumno.PanelAlumnoEdicion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author diego
 */
public class ControladorEdicionAlumno implements ActionListener{

    private PanelAlumnoEdicion view;
    private ControladorEspacioAlumno menuAlumno;

    private Alumno alumno = null;
    private List<Materia> materiaAgregar = new ArrayList<>();;
    
    public ControladorEdicionAlumno(ControladorEspacioAlumno menuAlumno, PanelAlumnoEdicion view) {
        this.view = view;
        this.menuAlumno = menuAlumno;
        asignarControlador();
    }

    public void setView(PanelAlumnoEdicion view) {
        this.view = view;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.view.getBt_ConfirmAgregarMateria().addActionListener(this);
        this.view.getBt_ConfirmarCambios().addActionListener(this);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals( this.view.getBt_ConfirmAgregarMateria() ))
            agregarMateria();
        else if(e.getSource().equals( this.view.getBt_ConfirmarCambios() )){
            guardarCambios();
        }
    }
    
    public void ingresarAlumnoAEditar(Alumno alumno){
        this.alumno = alumno;
        view.getTxt_SelectAlumno().setText( String.valueOf(alumno.getNombre()) +" "+ String.valueOf(alumno.getApellido()));
        view.getTxt_SelectAlumnoDni().setText( String.valueOf(alumno.getDni()) );
        view.getTxt_AlumnoNombre().setText(alumno.getNombre());
        view.getTxt_AlumnoApellido().setText(alumno.getApellido());
        view.getTxt_AlumnoResidencia().setText(alumno.getDomicilio());
        view.getTxt_AlumnoTelefono().setText(alumno.getTelefono());
        view.getTxt_AlumnoCarreraNombre().setText(alumno.getInscripcion().getCarrera().getNombre());
        view.getTxt_AlumnoCarreraCodigo().setText(String.valueOf(alumno.getInscripcion().getCarrera().getCodigo()));
        view.getTxt_InscripcionFecha().setText(String.valueOf(alumno.getInscripcion().getFechaInscripcion().format(DateTimeFormatter.ISO_DATE)));
        //Listamos las materias
        DefaultListModel modeloAlumnoMaterias = new DefaultListModel();
        for(int i=0; i< this.alumno.getCursados().size(); i++)
            modeloAlumnoMaterias.addElement(this.alumno.getCursados().get(i).getMateria());
        
        this.view.getList_AlumnoMaterias().setModel(modeloAlumnoMaterias);
        
        DefaultComboBoxModel modeloMaterias = new DefaultComboBoxModel<>();
        List<Materia> materias = Materia.listarMaterias();
        for(Materia materia: materias){
            boolean flagSiCursa = false;
            for(Cursado cursadoAlumno: this.alumno.getCursados())
                if(materia.getCodigo() == cursadoAlumno.getMateria().getCodigo()){
                    flagSiCursa = true;
                    break;
                }
            if(!flagSiCursa)
                modeloMaterias.addElement(materia);
        }
        
        
        this.view.getCb_SelectMateria().setModel( modeloMaterias );
        this.view.getList_AlumnoMaterias().setModel( modeloAlumnoMaterias );
    }

    private void agregarMateria() {
        this.materiaAgregar = new ArrayList<>();
        int index = this.view.getCb_SelectMateria().getSelectedIndex();
        DefaultComboBoxModel modeloMateria = new DefaultComboBoxModel();
        Materia materia = (Materia) this.view.getCb_SelectMateria().getItemAt(index);
        
        for(int i = 0; i < this.view.getCb_SelectMateria().getModel().getSize(); i++){
            if(i != index)
                modeloMateria.addElement(this.view.getCb_SelectMateria().getItemAt(i));
        }
        this.view.getCb_SelectMateria().setModel(modeloMateria);
        DefaultListModel modeloLista = (DefaultListModel) this.view.getList_AlumnoMaterias().getModel();
        
        modeloLista.addElement(materia);
        this.materiaAgregar.add(materia);
        this.view.getList_AlumnoMaterias().setModel(modeloLista);
        
        modeloMateria = null;
        modeloLista = null;
        materia = null;
    
    }
    
    private void guardarCambios(){
        
        this.alumno.setNombre( this.view.getTxt_AlumnoNombre().getText() );
        this.alumno.setApellido( this.view.getTxt_AlumnoApellido().getText() );
        this.alumno.setDomicilio( this.view.getTxt_AlumnoResidencia().getText() );
        this.alumno.setTelefono( this.view.getTxt_AlumnoTelefono().getText() );
        
        
        
        
        Alumno.updateAlumno(alumno);
        // Asosiamos las materias al alumno, creando un cursado.
        for(int i = 0; i < this.materiaAgregar.size(); i++){
            Cursado cursado = new Cursado();
            cursado.setAlumno(alumno);
            cursado.setMateria(this.materiaAgregar.get(i));
            Cursado.createCursado(cursado);
        }
        
        this.menuAlumno.recargarPlanilla();
    }
}
