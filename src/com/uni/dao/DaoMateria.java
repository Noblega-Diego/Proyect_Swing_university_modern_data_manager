
package com.uni.dao;
import com.uni.dao.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.uni.model.Materia;
import com.uni.model.Profesor;
import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;
/**
 *
 * @author diego
 */
public class DaoMateria extends Conexion{
    private static String QUERY_SELECT_MATERIAS = "CALL `GET_MATERIAS`()";
    private static String QUERY_UPDATE_MATERIA = "CALL `UPDATE_MATERIA`(?,?,?)";
    private static String QUERY_CREATE_MATERIA = "CALL `CREATE_MATERIA`(?,?,?)";
    private static String QUERY_DELETE_MATERIA = "CALL `CLEAR_MATERIA`(?)";
    //Consultas de filtrado
    private static String QUERY_FIL_SELECT_MATERIA = "CALL `GET_MATERIA`(?)";
    private static String QUERY_FIL_SELECT_MATERIAS_WHERE_PROFESOR = "CALL `GET_MATERIAS_PROFESOR`(?)";
    private static String QUERY_FIL_SELECT_MATERIAS_WHERE_NOT_PROFESOR = "CALL `GET_MATERIAS_NOT_PROFESOR`(?)";
            
    public static List<Materia> read(){
        Connection conn = null;
        PreparedStatement pe = null;
        ResultSet rs = null;
        List<Materia> listaMaterias = new ArrayList<Materia>();
        
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_SELECT_MATERIAS);
            rs = pe.executeQuery();// realizamos ua consuta del tipo select, esperando la respuesta de tipo ResultSet
            while(rs.next()){
                Materia materia = new Materia();
                materia.setCodigo(rs.getInt(1));
                materia.setNombre(rs.getString(2));
                if(rs.getInt(3)!= 0);{
                    Profesor profesor = DaoProfesor.select(rs.getInt(3));
                    materia.setProfesor(profesor);
                }
                listaMaterias.add(materia);
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
        
        
        
        return listaMaterias;
    }
    
    
    
    public static void update(Materia materia){
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_UPDATE_MATERIA);
            pe.setString(1, materia.getNombre());
            if(materia.getProfesor() != null)
                pe.setInt(2, materia.getProfesor().getDni());
            else
                pe.setNull(2, 0);
            pe.setInt(3, materia.getCodigo());
            
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
    
    public static void delete(int codigo){
        
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_DELETE_MATERIA);
            pe.setInt(1, codigo);
            
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

    
    public static void agregar(Materia materia){
        
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_CREATE_MATERIA);
            pe.setString(1, materia.getNombre());
            if(materia.getProfesor() != null)
                pe.setInt(2, materia.getProfesor().getDni());
            else
                pe.setNull(2, 0);
            pe.setInt(3, materia.getCodigo());
            
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

    //filter
    public static List<Materia> readFilProfesor(int dniProfesor, boolean coincide) {
        Connection conn = null;
        PreparedStatement pe = null;
        ResultSet rs = null;
        List<Materia> listaMaterias = new ArrayList<Materia>();
        
        try{
            conn = getConnection();
            if(coincide)
                pe = conn.prepareStatement(QUERY_FIL_SELECT_MATERIAS_WHERE_PROFESOR);
            else
                pe = conn.prepareStatement(QUERY_FIL_SELECT_MATERIAS_WHERE_NOT_PROFESOR);
            pe.setInt(1, dniProfesor);
            rs = pe.executeQuery();// realizamos ua consuta del tipo select, esperando la respuesta de tipo ResultSet
            while(rs.next()){
                Materia materia = new Materia();
                materia.setCodigo(rs.getInt(1));
                materia.setNombre(rs.getString(2));
                if(rs.getInt(3)!= 0);{
                    Profesor profesor = DaoProfesor.select(rs.getInt(3));
                    materia.setProfesor(profesor);
                }
                listaMaterias.add(materia);
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
        
        
        
        return listaMaterias;
    }
    
    
    public static Materia select(int codigo){
        Connection conn = null;
        PreparedStatement pe = null;
        ResultSet rs = null;
        Materia materia = null;
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_FIL_SELECT_MATERIA);
            pe.setInt(1, codigo);
            rs = pe.executeQuery();// realizamos ua consuta del tipo select, esperando la respuesta de tipo ResultSet
            materia = new Materia();
            rs.next();
            materia.setCodigo(rs.getInt(1));
            materia.setNombre(rs.getString(2));
            if(rs.getInt(3)!= 0);{
                Profesor profesor = DaoProfesor.select(rs.getInt(3));
                materia.setProfesor(profesor);
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
        
        
        
        return materia;
    }
    
}
