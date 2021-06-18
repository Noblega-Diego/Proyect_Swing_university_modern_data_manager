
package com.uni.dao;
import com.uni.dao.Conexion;
import com.uni.model.Carrera;
import com.uni.model.Inscripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;
import java.time.LocalDate;
/**
 *
 * @author diego
 */
public class DaoInscripcion extends Conexion{
    private static String QUERY_SELECT_INSCRIPCIONES = "CALL `GET_INSCRIPCIONES`()";
    private static String QUERY_UPDATE_INSCRIPCION = "CALL `UPDATE_INSCRIPCION`(?,?,?,?,?,?)";
    private static String QUERY_CREATE_INSCRIPCION = "CALL `CREATE_INSCRIPCION`(?,?,?,?,?)";
    private static String QUERY_DELETE_INSCRIPCION = "CALL `CLEAR_INSCRIPCION`(?)";
    //Consultas de filtrado
    private static String QUERY_SELECT_INSCRIPCION = "CALL `GET_INSCRIPCION`(?)";
    
    public static List<Inscripcion> read(){
        Connection conn = null;
        PreparedStatement pe = null;
        ResultSet rs = null;
        List<Inscripcion> listaInscripcion = new ArrayList<Inscripcion>();
        
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_SELECT_INSCRIPCIONES);
            rs = pe.executeQuery();// realizamos ua consuta del tipo select, esperando la respuesta de tipo ResultSet
            
            while(rs.next()){
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setCodigo(rs.getInt(1));
                inscripcion.setNombre(rs.getString(2));
                String[] fecha = rs.getString(3).split("-");
                Carrera carrera = Carrera.selecionarCarrera(rs.getInt(4));
                inscripcion.setCarrera(carrera);
                inscripcion.setFechaInscripcion(LocalDate.of(Integer.valueOf(fecha[0]), Integer.valueOf(fecha[1]), Integer.valueOf(fecha[2])));
                listaInscripcion.add(inscripcion);
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
        
        return listaInscripcion;
    }
    
    
    
    public static void update(Inscripcion inscripcion){
//        Connection conn = null;
//        PreparedStatement pe = null;
//        try{
//            
//            conn = getConnection();
//            pe = conn.prepareStatement(QUERY_UPDATE_INSCRIPCION);
//            pe.setString(1, inscripcion.getNombre());
//            pe.setString(2, inscripcion.getApellido());
//            pe.setString(3, inscripcion.getFechaNacimiento().format(DateTimeFormatter.ISO_DATE));
//            pe.setString(4, inscripcion.getDomicilio());
//            pe.setString(5, inscripcion.getTelefono());
//            pe.setInt(6, inscripcion.getDni());
//            
//            pe.executeUpdate();//realizamos la peticion tipo update
//            System.out.println("Update realizado con exito");
//        }catch(SQLException e){
//            System.out.println("Error al actualizar: " + e);
//        }catch(Exception e){
//            System.err.println("Error desconocido: " + e);
//        }
//        finally{
//            //finalizamos la conexion
//            close(conn);
//            close(pe);
//        }
    }
    
    public static void delete(int dni){
        
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_DELETE_INSCRIPCION);
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
    public static void agregar(Inscripcion inscripcion){
//        
//        Connection conn = null;
//        PreparedStatement pe = null;
//        try{
//            conn = getConnection();
//            pe = conn.prepareStatement(QUERY_CREATE_INSCRIPCION);
//            pe.setString(1, inscripcion.getNombre());
//            pe.setString(2, inscripcion.getApellido());
//            pe.setString(3, inscripcion.getFechaNacimiento().format(DateTimeFormatter.ISO_DATE));
//            pe.setString(4, inscripcion.getDomicilio());
//            pe.setString(5, inscripcion.getTelefono());
//            pe.setInt(6, inscripcion.getDni());
//            
//            pe.executeUpdate();//realizamos la peticion tipo create
//        }catch(SQLException e){
//            System.out.println("Error al eliminar " + e);
//        }catch(Exception e){
//            System.err.println("Error desconocido: " + e);
//        }
//        finally{
//            //finalizamos la conexion
//            close(conn);
//            close(pe);
//        }
    }
    
    public static Inscripcion select(int codigo){
        Connection conn = null;
        PreparedStatement pe = null;
        ResultSet rs = null;
        Inscripcion inscripcion = null;
        if(codigo != 0)
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_SELECT_INSCRIPCION);
            pe.setInt(1, codigo);
            rs = pe.executeQuery();// realizamos una consuta del tipo select, esperando la respuesta de tipo ResultSet
            rs.next();
            inscripcion = new Inscripcion();
            inscripcion.setNombre(rs.getString(1));
            inscripcion.setNombre(rs.getString(2));
            String[] fecha = rs.getString(3).split("-");
            inscripcion.setFechaInscripcion(LocalDate.of(
                    Integer.valueOf(fecha[0]), 
                    Integer.valueOf(fecha[1]), 
                    Integer.valueOf(fecha[2])));
            inscripcion.setCarrera(Carrera.selecionarCarrera(rs.getInt(4)));

            
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
        
        return inscripcion;
    }
}
