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
    public void incluiFuncionario(funcionario insere){
        
        Connection con = connectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("call novo_funcionario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1,insere.getLogradouro());
            stmt.setString(2,insere.getBairro());
            stmt.setInt(3,insere.getNumero());
            stmt.setString(4,insere.getCep());
            stmt.setString(5,insere.getCidade());
            stmt.setString(6,insere.getComplemento());
            stmt.setString(7,insere.getUf());
            stmt.setString(8,insere.getCargo());
            stmt.setString(9,insere.getNome_func());
            stmt.setString(10,insere.getSexo());
            stmt.setString(11,insere.getEmail());
            stmt.setString(12,insere.getTelefone());
            stmt.setString(13,insere.getNaturalidade());
            stmt.setString(14,insere.getUf_func());
            stmt.setString(15,insere.getRg());
            stmt.setString(16,insere.getCpf());
            stmt.setString(17,insere.getData_nasci());
            stmt.setString(18,insere.getLogin());
            stmt.setString(19,insere.getSenha());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar os dados!\n"+ex);
        }finally{
            connectionFactory.closeConnection(con,stmt);
        }
    }
}
