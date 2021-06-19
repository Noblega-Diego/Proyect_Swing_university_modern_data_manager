package com.uni.controller;


import com.uni.model.Alumno;
import com.uni.model.Cursado;
import com.uni.model.Materia;
import com.uni.view.submenus.alumno.PanelAlumnoVisualizar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author diego
 */
public class ControladorVisualizarAlumno implements ActionListener{

    private PanelAlumnoVisualizar view;
    private ControladorEspacioAlumno menuAlumno;

    private Alumno alumno = null;
    private List<Cursado> cursados;
    
    public ControladorVisualizarAlumno(ControladorEspacioAlumno menuAlumno, PanelAlumnoVisualizar view) {
        this.view = view;
        this.menuAlumno = menuAlumno;
        asignarControlador();
    }

    public void setView(PanelAlumnoVisualizar view) {
        this.view = view;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.view.getBt_ConfirmSelect().addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals( this.view.getBt_ConfirmSelect() ))
            seleccionarMateria();
    }
    
    public void ingresarAlumnoAVisualizar(Alumno alumno){
        this.alumno = alumno;
        view.getTxt_AlumnoDni().setText(String.valueOf(alumno.getDni()));
        view.getTxt_AlumnoNombre().setText(alumno.getNombre());
        view.getTxt_AlumnoApellido().setText(alumno.getApellido());
        view.getTxt_AlumnoResidencia().setText(alumno.getDomicilio());
        view.getTxt_AlumnoTelefono().setText(alumno.getTelefono());
        view.getTxt_AlumnoCarreraNombre().setText(alumno.getInscripcion().getCarrera().getNombre());
        view.getTxt_AlumnoCarreraCodigo().setText(String.valueOf(alumno.getInscripcion().getCarrera().getCodigo()));
        view.getTxt_InscripcionFecha().setText(String.valueOf(alumno.getInscripcion().getFechaInscripcion().format(DateTimeFormatter.ISO_DATE)));
        DefaultComboBoxModel modeloMaterias = new DefaultComboBoxModel<>();
        
        //Cargamos los Las materias y cursos del Alumno
        this.cursados = Cursado.seleccionarCursados(alumno.getDni());
        for(Cursado cursado: this.cursados){
            modeloMaterias.addElement(cursado.getMateria());
            
        }
        view.getCb_SelectMateria().setModel( modeloMaterias );
    }

    private void seleccionarMateria() {
        Materia materia = (Materia) this.view.getCb_SelectMateria().getSelectedItem();
        if(materia != null){
            Cursado cursado = null;
            for(Cursado curs: this.cursados)
                if(curs.getMateria().equals(materia))
                    cursado = curs;
            if(materia.getProfesor() != null) 
                this.view.getTxt_MateriaProfesorNombre().setText(materia.getNombre());
            this.view.getLb_CursadoNota().setText( String.valueOf(cursado.getNota()));
        }
    }
}
