
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
        }
        finally{
            //finalizamos la conexion
            close(conn);
            close(rs);
        }
        
        return listaCarreras;
    }
}
