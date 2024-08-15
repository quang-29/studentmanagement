/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.config;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author My Laptop
 */
public class DBHelper {
    
    private static final String className= "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/qlsv";
    private static final String user = "root";
    private static final String password = "";
    
    public static Connection getConnection(){
        
        Connection connection = null;
        try {
            Class.forName(DBHelper.className);
            connection = DriverManager.getConnection(DBHelper.url, DBHelper.user, DBHelper.password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }
    
}
