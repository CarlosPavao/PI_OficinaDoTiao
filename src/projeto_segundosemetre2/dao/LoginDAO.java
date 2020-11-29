/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex
 */
public class LoginDAO {
    
public boolean validausuario(String login, String senha){
        Connection conector = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean valida = false;
        
        try {
            stmt= conector.prepareStatement("SELECT * FROM funcionario WHERE cpf = ? and senha = ?");
            stmt.setString(1,login);
            stmt.setString(2,senha);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                
                valida = true;
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!\n"+ex);
        }finally{
            ConnectionFactory.closeConnection(conector,stmt, rs);
        }
        
        return valida;
    }
}