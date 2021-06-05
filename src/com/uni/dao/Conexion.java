package com.uni.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author SkylakeFrost
 */
public class Conexion {

    /*Clase Estandar Para Conectar a Base De Datos MySQL
    Se Requiere La Libreria/Conector MySQL Importado.
     */
    //DB Config. Editar En Caso De Tener Otras Diferentes Ej. Nombre DB, User, Pass, etc
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATA_BASE = "sgauni512";
    private static final String SSL = "?useSSL=false";
    private static final String URL = "jdbc:mysql://localhost/";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "mysql";

    public static Connection getConnection() {
        Connection conexion = null;

        try {
            Class.forName(JDBC_DRIVER);
            conexion = DriverManager.getConnection(URL + DATA_BASE + SSL, USER_NAME, PASSWORD);
            if (conexion != null) {
                System.out.println("Conexión Exitosa!");
                System.out.println("Conectado a : " + URL + DATA_BASE + SSL);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error De Conexión! :  " + e);
            JOptionPane.showMessageDialog(null, "Error De Conexión A Base De Datos!\n " + e);
            System.exit(0);

        }

        return conexion;

    }
////Metodos para finalizar la conexion  ya que no se usa un pool de conexiones como el de mysql u oracle
//

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
