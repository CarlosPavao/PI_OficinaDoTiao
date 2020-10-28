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
import model.bean.carro;

/**
 *
 * @author Alex
 */
public class carroDAO {
    public void incluiCarro(carro inseri){
        
        Connection con = connectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO carro(placa,modelo,fabricante,ano_fab,cor,km,ano_modelo,informacoes) VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1,inseri.getPlcaca());
            stmt.setString(2,inseri.getModelo());
            stmt.setString(3,inseri.getFabricante());
            stmt.setString(4,inseri.getAno_fab());
            stmt.setString(5,inseri.getCor());
            stmt.setString(6,inseri.getKm());
            stmt.setString(7,inseri.getAno_modelo());
            stmt.setString(8,inseri.getInformacoes());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Carro cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar os dados!\n"+ex);
        }finally{
            connectionFactory.closeConnection(con,stmt);
        }
    }
}
