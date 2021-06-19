
package com.uni.dao;
import com.uni.dao.Conexion;
import com.uni.model.Cursado;
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
public class DaoCursado extends Conexion{
    private static String QUERY_SELECT_CURSADOS = "CALL `GET_CURSADOS`()";
    private static String QUERY_UPDATE_CURSADO = "CALL `UPDATE_CURSADO`(?,?,?)";
    private static String QUERY_CREATE_CURSADO = "CALL `CREATE_CURSADO`(?,?,?)";
    private static String QUERY_DELETE_CURSADO = "CALL `CLEAR_CURSADO`(?)";
    //Consultas de filtrado
    private static String QUERY_FIL_SELECT_CURSADO_WHERE_ALUMNO = "CALL `GET_CURSADO_ALUMNO`(?)";
    private static String QUERY_FIL_CLEAR_CURSADO_WHERE_ALUMNO = "CALL `CLEAR_CURSADO_ALUMNO`(?)";
            
    public static List<Materia> read(){
        Connection conn = null;
        PreparedStatement pe = null;
        ResultSet rs = null;
        List<Materia> listaMaterias = new ArrayList<Materia>();
        
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_SELECT_CURSADOS);
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
    
    
    
    public static void update(Cursado cursado){
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_UPDATE_CURSADO);
            pe.setString(1, String.valueOf(cursado.getAlumno().getDni()));
            pe.setString(2, String.valueOf(cursado.getMateria().getCodigo()));
            pe.setString(3, String.valueOf(cursado.getNota()));
            
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
    
    public static void delete(int codigoMateria, int dniAlumno){
        
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_DELETE_CURSADO);
            pe.setInt(1, codigoMateria);
            pe.setInt(2, dniAlumno);
            
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

    
    public static void agregar(Cursado cursado){
        
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_CREATE_CURSADO);
            pe.setInt(1, cursado.getAlumno().getDni());
            pe.setInt(2, cursado.getMateria().getCodigo());
            pe.setInt(3, cursado.getNota());
            pe.executeUpdate();//realizamos la peticion tipo create
        }catch(SQLException e){
            System.out.println("Error al agregarCursado " + e);
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
    public static List<Cursado> readFilAlumno(int dniAlumno) {
        Connection conn = null;
        PreparedStatement pe = null;
        ResultSet rs = null;
        List<Cursado> listaCursado = new ArrayList<Cursado>();
        
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_FIL_SELECT_CURSADO_WHERE_ALUMNO);
            pe.setInt(1, dniAlumno);
            rs = pe.executeQuery();// realizamos ua consuta del tipo select, esperando la respuesta de tipo ResultSet
            while(rs.next()){
                Cursado cursado = new Cursado();
                Materia materia = DaoMateria.select(rs.getInt(2));
                cursado.setMateria(materia);
                cursado.setNota(rs.getInt(3));
                listaCursado.add(cursado);
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
        
        
        
        return listaCursado;
    }

    public static void deleteCursadoAlumno(int dni) {
        Connection conn = null;
        PreparedStatement pe = null;
        try{
            conn = getConnection();
            pe = conn.prepareStatement(QUERY_FIL_CLEAR_CURSADO_WHERE_ALUMNO);
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
}
