package com.uni.controller;


import com.uni.model.Alumno;
import com.uni.model.Carrera;
import com.uni.model.Inscripcion;
import com.uni.view.submenus.alumno.PanelAlumnoAgregacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author diego
 */
public class ControladorAgregarAlumno implements ActionListener{

    private PanelAlumnoAgregacion view;
    private ControladorEspacioAlumno menuAlumno;
    
    public ControladorAgregarAlumno(ControladorEspacioAlumno menuAlumno, PanelAlumnoAgregacion view) {
        this.view = view;
        this.menuAlumno = menuAlumno;
        asignarControlador();
    }

    public void setView(PanelAlumnoAgregacion view) {
        this.view = view;
        asignarControlador();
    }
    
    private void asignarControlador(){
        this.view.getBt_confirmacion().addActionListener(this);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals( this.view.getBt_confirmacion()))
            agregarAlumno();
    }
    
    public void cargarCarreras(){
        DefaultComboBoxModel modeloCarreras = new DefaultComboBoxModel();
        List<Carrera> carreras = Carrera.listarCarreras();
        for(Carrera carrera : carreras)
            modeloCarreras.addElement(carrera);
       
        this.view.getCb_CarreraIngresar().setModel(modeloCarreras);
    }
    
    private void agregarAlumno(){
        Carrera carrera = (Carrera) this.view.getCb_CarreraIngresar().getSelectedItem();
        if(carrera != null){
            Alumno alumno = new Alumno();
            alumno.setNombre( this.view.getTxt_AlumnoNombre().getText() );
            alumno.setApellido( this.view.getTxt_AlumnoApellido().getText() );
            alumno.setDomicilio( this.view.getTxt_AlumnoResidencia().getText() );
            Calendar fecha = view.getDc_AlumnoFechaNacimiento().getCalendar();
            alumno.setFechaNacimiento(LocalDate.of(
                    fecha.get(Calendar.YEAR),  //Año
                    fecha.get(Calendar.MONTH)+1,  //Mes
                    fecha.get(Calendar.DAY_OF_MONTH)));//dia
            alumno.setTelefono( this.view.getTxt_AlumnoTelefono().getText() );
            alumno.setDni(Integer.valueOf(this.view.getTxt_AlumnoDni().getText()) );
            Inscripcion inscripcion = new Inscripcion();
            ArrayList<Alumno> alumnos = new ArrayList<>();
            alumnos.add(alumno);
            inscripcion.setAlumnos(alumnos);
            inscripcion.setCarrera(carrera);
            inscripcion.setNombre(this.view.getTxt_AlumnoApellido().getText());
            
            Calendar fechaInscripcion = view.getDc_FechaInscripcion().getCalendar();
            inscripcion.setFechaInscripcion(LocalDate.of(
                    fechaInscripcion.get(Calendar.YEAR),  //Año
                    fechaInscripcion.get(Calendar.MONTH)+1,  //Mes
                    fechaInscripcion.get(Calendar.DAY_OF_MONTH)));//dia
            inscripcion.setCodigo(Integer.valueOf( this.view.getTxt_InscripcionCodigo().getText()));
            alumno.setInscripcion(inscripcion);
            Inscripcion.createInscripcion(inscripcion);
            Alumno.createAlumno(alumno);
            this.menuAlumno.recargarPlanilla();
        }
    }
}
