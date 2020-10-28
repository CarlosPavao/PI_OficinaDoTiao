/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.connectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.cliente;
import model.bean.endereco;

/**
 *
 * @author Alex
 */
public class enderecoDAO {
        public void incluiEndere(endereco inseri){
        
        Connection con = connectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO endereco(logradouro,bairro,numero,cep,cidade,complemento,uf) VALUES(?,?,?,?,?,?,?)");
            stmt.setString(1,inseri.getLogradouro());
            stmt.setString(2,inseri.getBairro());
            stmt.setInt(3,inseri.getNumero());
            stmt.setString(4,inseri.getCep());
            stmt.setString(5,inseri.getCidade());
            stmt.setString(6,inseri.getComplemento());
            stmt.setString(7,inseri.getUf());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Endereço cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar os dados!\n"+ex);
        }finally{
            connectionFactory.closeConnection(con,stmt);
        }
    }
}
