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
import model.bean.funcionario;

/**
 *
 * @author Alex
 */
public class funcionarioDAO {
    public void incluiFuncionario(funcionario inseri){
        
        Connection con = connectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO funcionario(nome_func,email,naturalidade,celular,rg,cpf,sexo,uf,data_nasci) VALUES(?,?,?,?,?,?,?,?,?)");
            stmt.setString(1,inseri.getNome_funcionario());
            stmt.setString(2,inseri.getEmail());
            stmt.setString(3,inseri.getNaturalidade());
            stmt.setString(4,inseri.getCelular());
            stmt.setString(5,inseri.getRg());
            stmt.setString(6,inseri.getCpf());
            stmt.setString(7,inseri.getSexo());
            stmt.setString(8,inseri.getUf());
            stmt.setString(9,inseri.getData_nasci());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar os dados!\n"+ex);
        }finally{
            connectionFactory.closeConnection(con,stmt);
        }
    }
}
