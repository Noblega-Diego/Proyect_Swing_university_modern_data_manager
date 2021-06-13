
package com.uni.dao;
import com.uni.dao.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.uni.model.Profesor;
import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author diego
 */
public class DaoProfesor extends Conexion{
    private static String QUERY_SELECT_PROFESOR = "CALL `GET_PROFESORES`()";
    private static String QUERY_UPDATE_PROFESOR = "CALL `UPDATE_PROFESOR`(?,?,?,?,?,?)";
    private static String QUERY_CREATE_PROFESOR = "CALL `CREATE_PROFESOR`(?,?,?)";
    private static String QUERY_DELETE_PROFESOR = "CALL `CLEAR_PROFESOR`(?)";
            
    public static List<Profesor> read(){
        Connection conn = null;
        PreparedStatement pe = null;
        ResultSet rs = null;
        List<Profesor> listaProfesores = new ArrayList<Profesor>();
        
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_SELECT_PROFESOR);
            rs = pe.executeQuery();// realizamos ua consuta del tipo select, esperando la respuesta de tipo ResultSet
            
            while(rs.next()){
                Profesor p = new Profesor();
                p.setDni(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setApellido(rs.getString(3));
                System.out.println(rs.getString(4));
                String[] fecha = rs.getString(4).split("-");
                p.setFedchaNacimiento(LocalDate.of(Integer.valueOf(fecha[0]), Integer.valueOf(fecha[1]), Integer.valueOf(fecha[2])));
                p.setDomicilio(rs.getString(5));
                p.setTelefono(rs.getString(6));
                listaProfesores.add(p);
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
        
        return listaProfesores;
    }
    
    
    
    public static void update(Profesor profesor){
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_UPDATE_PROFESOR);
            pe.setString(1, profesor.getNombre());
            pe.setString(2, profesor.getApellido());
            pe.setString(3, profesor.getFedchaNacimiento().format(DateTimeFormatter.ISO_DATE));
            pe.setString(4, profesor.getDomicilio());
            pe.setString(5, profesor.getTelefono());
            pe.setInt(6, profesor.getDni());
            
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
            pe = conn.prepareStatement(QUERY_DELETE_PROFESOR);
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
    public static void agregar(Profesor profesor){
        
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_CREATE_PROFESOR);
            pe.setString(1, profesor.getNombre());
            
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
}
