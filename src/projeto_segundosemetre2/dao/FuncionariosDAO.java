/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Telas.MenuPrincipal;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Funcionarios;

/**
 *
 * @author Alex
 */
public class FuncionariosDAO {
    private Connection con;

    public FuncionariosDAO() {
        this.con = ConnectionFactory.getConnection();
    }

    //Metodo cadastrar Funcionario
    public void cadastrarFuncionarios(Funcionarios obj) {
        try {

            String sql = "call novo_funcionario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome_func());
            stmt.setString(2, obj.getSexo());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getCelular());
            stmt.setString(5, obj.getRg());
            stmt.setString(6, obj.getData_nasci());
            stmt.setString(7, obj.getCargo());
            stmt.setString(8, obj.getCpf());
            stmt.setString(9, obj.getSenha());
            stmt.setString(10, obj.getPrivilegios());
            stmt.setString(11, obj.getCep());
            stmt.setString(12, obj.getLogradouro());
            stmt.setInt(13, obj.getNumero());
            stmt.setString(14, obj.getComplemento());
            stmt.setString(15, obj.getBairro());
            stmt.setString(16, obj.getCidade());
            stmt.setString(17, obj.getUf());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }

    //Metodo Alterar Funcionario
    public void alterarFuncionario(Funcionarios obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "update funcionario  set nome_func=?, sexo=?, email=?, celular=?, rg=?, data_nasci=? cargo=?, cpf=?, senha=?, privilegios=? cep=?, "
                    + "logradouro=?, numero=?,complemento=?,bairro=?,cidade=?, estado=?, uf=?  where id =?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome_func());
            stmt.setString(2, obj.getSexo());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getCelular());
            stmt.setString(5, obj.getRg());
            stmt.setString(6, obj.getData_nasci());
            stmt.setString(7, obj.getCargo());
            stmt.setString(8, obj.getCpf());
            stmt.setString(9, obj.getSenha());
            stmt.setString(10, obj.getPrivilegios());
            stmt.setString(11, obj.getCep());
            stmt.setString(12, obj.getLogradouro());
            stmt.setInt(13, obj.getNumero());
            stmt.setString(14, obj.getComplemento());
            stmt.setString(15, obj.getBairro());
            stmt.setString(16, obj.getCidade());
            stmt.setString(17, obj.getUf());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
    }

    //Metodo Excluir Funcionario
    public void excluirFuncionario(Funcionarios obj) {
        try {

            //1 passo  - criar o comando sql
            String sql = "delete from funcionario  where id = ?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId_funcionario());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }

    //Metodo Listar Todos Funcionarios
    public List<Funcionarios> listarFuncionarios() {
        try {

            //1 passo criar a lista
            List<Funcionarios> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from funcionario";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId_funcionario(rs.getInt("id"));
                obj.setNome_func(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    //metodo consultaCliente  por Nome
    public Funcionarios consultaPorNome(String nome) {
        try {
            //1 passo - criar o sql , organizar e executar.
            String sql = "select * from funcionario  where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Funcionarios obj = new Funcionarios();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado!");
            return null;
        }
    }

    //Metodo listaFuncionarioPorNome - retorna uma lista
    public List<Funcionarios> listarFuncionariosPorNome(String nome) {
        try {

            //1 passo criar a lista
            List<Funcionarios> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from funcionario where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId_funcionario(rs.getInt("id"));
                obj.setNome_func(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));

                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setPrivilegios(rs.getString("privilegios"));

                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }

    //Metodo efetuaLogin
    public void efetuaLogin(String cpf, String senha ) {
        try {

            //1 passo - SQL
            String sql = "select * from funcionarios where email = ? and senha = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(8, cpf);
            stmt.setString(9, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                //Usuario logou

                //Caso o usuario seja do tipo admin
                if (rs.getString("nivel_acesso").equals("Admin")) {

                    JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
                    MenuPrincipal tela = new MenuPrincipal();
                    tela.setVisible(true);
                } 

//Caso o usuario seja do tipo limitado 
                else if (rs.getString("privilegios").equals("Usuário")) {
                    
                    JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
                    MenuPrincipal tela = new MenuPrincipal();
                    
                    //Desabilitar os menus
                    tela.menu_posicao.setEnabled(false);
                    tela.menu_controlevendas.setVisible(false);
                   
                    tela.setVisible(true);

                }

            } else {
                //Dados incorretos
                JOptionPane.showMessageDialog(null, "Dados incorretos!");
                new MenuPrincipal().setVisible(true);

            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro : " + erro);
        }

    }
}
