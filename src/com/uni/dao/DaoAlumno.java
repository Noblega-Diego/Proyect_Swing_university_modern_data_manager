
package com.uni.dao;
import com.uni.dao.Conexion;
import com.uni.model.Alumno;
import com.uni.model.Cursado;
import com.uni.model.Inscripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author diego
 */
public class DaoAlumno extends Conexion{
    private static String QUERY_SELECT_ALUMNOS = "CALL `GET_ALUMNOS`()";
    private static String QUERY_UPDATE_ALUMNO = "CALL `UPDATE_ALUMNO`(?,?,?,?,?,?)";
    private static String QUERY_CREATE_ALUMNO = "CALL `CREATE_ALUMNO`(?,?,?,?,?,?)";
    private static String QUERY_DELETE_ALUMNO = "CALL `CLEAR_ALUMNO`(?)";
    //Consultas de filtrado
    private static String QUERY_FIL_SELECT_ALUMNO = "CALL `GET_ALUMNO`(?)";
    
    public static List<Alumno> read(){
        Connection conn = null;
        PreparedStatement pe = null;
        ResultSet rs = null;
        List<Alumno> listaAlumnos = new ArrayList<Alumno>();
        
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_SELECT_ALUMNOS);
            rs = pe.executeQuery();// realizamos ua consuta del tipo select, esperando la respuesta de tipo ResultSet
            
            while(rs.next()){
                Alumno alumno = new Alumno();
                alumno.setDni(rs.getInt(1));
                alumno.setNombre(rs.getString(2));
                alumno.setApellido(rs.getString(3));
                String[] fecha = rs.getString(4).split("-");
                alumno.setFechaNacimiento(LocalDate.of(Integer.valueOf(fecha[0]), Integer.valueOf(fecha[1]), Integer.valueOf(fecha[2])));
                alumno.setDomicilio(rs.getString(5));
                alumno.setTelefono(rs.getString(6));
                alumno.setInscripcion(Inscripcion.seleccionarInscripcion(rs.getInt(7)));
                alumno.setCursados(Cursado.seleccionarCursados(alumno.getDni()));
                listaAlumnos.add(alumno);
            }
            
        }catch(SQLException e){
            System.out.println("Error al obtener: " + e);
        }catch(Exception e){
            System.err.println("Error desconocido: " + e);
        }
        finally{
            //finalizamos la conexion
            close(conn);
            close(rs);
        }
        
        return listaAlumnos;
    }
    
    
    
    public static void update(Alumno alumno){
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_UPDATE_ALUMNO);
            pe.setString(1, alumno.getNombre());
            pe.setString(2, alumno.getApellido());
            pe.setString(3, alumno.getFechaNacimiento().format(DateTimeFormatter.ISO_DATE));
            pe.setString(4, alumno.getDomicilio());
            pe.setString(5, alumno.getTelefono());
            pe.setInt(6, alumno.getDni());
            
            pe.executeUpdate();//realizamos la peticion tipo update
            System.out.println("Update realizado con exito");
        }catch(SQLException e){
            System.out.println("Error al actualizar: " + e);
        }catch(Exception e){
            System.err.println("Error desconocido: " + e);
        }
        finally{
            //finalizamos la conexion
            close(conn);
            close(pe);
        }
    }
    
    public static void delete(int dni){
        
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_DELETE_ALUMNO);
            pe.setInt(1, dni);
            
            pe.executeUpdate();//realizamos la peticion tipo delete
        }catch(SQLException e){
            System.out.println("Error al eliminar " + e);
        }catch(Exception e){
            System.err.println("Error desconocido: " + e);
        }
        finally{
            //finalizamos la conexion
            close(conn);
            close(pe);
        }
    }

    
    //---------------------------------------------------------------EDITAR
    public static void agregar(Alumno alumno){
        
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_CREATE_ALUMNO);
            pe.setString(1, alumno.getNombre());
            pe.setString(2, alumno.getApellido());
            pe.setString(3, alumno.getFechaNacimiento().format(DateTimeFormatter.ISO_DATE));
            pe.setString(4, alumno.getDomicilio());
            pe.setString(5, alumno.getTelefono());
            pe.setInt(6, alumno.getDni());
            pe.setInt(7, alumno.getInscripcion().getCodigo());
            
            pe.executeUpdate();//realizamos la peticion tipo create
        }catch(SQLException e){
            System.out.println("Error al eliminar " + e);
        }catch(Exception e){
            System.err.println("Error desconocido: " + e);
        }
        finally{
            //finalizamos la conexion
            close(conn);
            close(pe);
        }
    }
    
    public static Alumno select(int dni){
        Connection conn = null;
        PreparedStatement pe = null;
        ResultSet rs = null;
        Alumno alumno = null;
        if(dni != 0)
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_FIL_SELECT_ALUMNO);
            pe.setInt(1, dni);
            rs = pe.executeQuery();// realizamos una consuta del tipo select, esperando la respuesta de tipo ResultSet
            rs.next();
            alumno = new Alumno();
            alumno.setDni(rs.getInt(1));
            alumno.setNombre(rs.getString(2));
            alumno.setApellido(rs.getString(3));
            String[] fecha = rs.getString(4).split("-");
            alumno.setFechaNacimiento(LocalDate.of(Integer.valueOf(fecha[0]), Integer.valueOf(fecha[1]), Integer.valueOf(fecha[2])));
            alumno.setDomicilio(rs.getString(5));
            alumno.setTelefono(rs.getString(6));
            
            alumno.setInscripcion(DaoInscripcion.select(rs.getInt(7)));
            alumno.setCursados(DaoCursado.readFilAlumno(alumno.getDni()));
        }catch(SQLException e){
            System.out.println("Error al obtener Profesor: " + e);
        }catch(Exception e){
            System.err.println("Error desconocido: " + e);
        }
        finally{
            //finalizamos la conexion
            close(conn);
            close(rs);
        }
        
        return alumno;
    }
}
