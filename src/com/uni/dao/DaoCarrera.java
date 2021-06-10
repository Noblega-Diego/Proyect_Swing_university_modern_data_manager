
package com.uni.dao;
import com.uni.dao.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.uni.model.Carrera;
import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;
/**
 *
 * @author diego
 */
public class DaoCarrera extends Conexion{
    private static String QUERY_SELECT_CARRERAS = "CALL `GET_CARRERAS`()";
    private static String QUERY_UPDATE_CARRERA = "CALL `UPDATE_CARRERA`(?, ?, ?)";
    private static String QUERY_CREATE_CARRERA = "CALL `CREATE_CARRERA`(?,?,?)";
    private static String QUERY_DELETE_CARRERA = "CALL `DELETE_CARRERA`(?)";
            
    public static List<Carrera> read(){
        Connection conn = null;
        PreparedStatement pe = null;
        ResultSet rs = null;
        List<Carrera> listaCarreras = new ArrayList<Carrera>();
        
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_SELECT_CARRERAS);
            rs = pe.executeQuery();// realizamos ua consuta del tipo select, esperando la respuesta de tipo ResultSet
            
            while(rs.next()){
                Carrera c = new Carrera();
                c.setCodigo(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setDuracion(rs.getString(3));
                listaCarreras.add(c);
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
        
        return listaCarreras;
    }
    
    public static void update(Carrera carrera){
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_UPDATE_CARRERA);
            pe.setString(1, carrera.getNombre());
            pe.setString(2, carrera.getDuracion());
            pe.setInt(3, carrera.getCodigo());
            
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
    
    public static void delete(int cod_carrera){
        
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_DELETE_CARRERA);
            pe.setInt(1, cod_carrera);
            
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

    public static void agregar(Carrera carrea){
        
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_CREATE_CARRERA);
            pe.setString(1, carrea.getNombre());
            pe.setString(2, carrea.getDuracion());
            pe.setInt(3, carrea.getCodigo());
            
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
