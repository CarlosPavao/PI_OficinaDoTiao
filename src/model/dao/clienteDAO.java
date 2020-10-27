/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.dao;
import java.sql.Connection;
import connection.connectionFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.cliente;

/**
 *
 * @author Alex
 */
public class clienteDAO {
    public void incluiClient(cliente inseri){
        
        Connection con = connectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cliente (nome,sexo,email,celular,rg,cpf,data_nasc,naturalidade,uf) VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1,inseri.getNome());
            stmt.setString(2,inseri.getSexo());
            stmt.setString(3,inseri.getEmail());
            stmt.setString(4,inseri.getCelular());
            stmt.setString(5,inseri.getRg());
            stmt.setString(6,inseri.getCpf());
            stmt.setString(7,inseri.getData_nasc());
            stmt.setString(8,inseri.getNaturalidade());
            stmt.setString(9,inseri.getUf());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                    } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inclui os dados!"+ex);
        }finally{
            connectionFactory.closeConnection(con,stmt);
        }
    }
}
