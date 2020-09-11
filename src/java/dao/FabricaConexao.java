/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gusta
 */
public class FabricaConexao {
    
    public static Connection abrirConexao() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop?useTimezone=true&serverTimezone=America/Sao_Paulo&zeroDateTimeBehavior=convertToNull", "root", "");              
    }  
    
    public static void fecharConexao(Connection conn, Statement psmt, ResultSet rs) throws SQLException {
       if(rs != null) { 
            rs.close();
       }
       conn.close();
       psmt.close();
    }
    
    
       
}

