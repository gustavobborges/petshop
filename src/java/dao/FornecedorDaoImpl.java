/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gusta
 */
public class FornecedorDaoImpl implements FornecedorDao {

    private Connection conexao;
    private PreparedStatement preparando;
    private ResultSet resultSet;

@Override
    public Object salvar(Object object) throws SQLException {
        Fornecedor fornecedor = (Fornecedor) object;
        String sql = "INSERT INTO fornecedor(nome, email, telefone, descricao, dataCadastro)"
                   + " VALUES(?, ?, ?, ?, ?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, fornecedor.getNome());
            preparando.setString(2, fornecedor.getEmail());
            preparando.setString(3, fornecedor.getTelefone());
            preparando.setString(4, fornecedor.getDescricao());
            preparando.setDate(5, new Date(fornecedor.getDataCadastro().getTime()));
            preparando.executeUpdate();
            resultSet = preparando.getGeneratedKeys();
            resultSet.next();
            fornecedor.setId(resultSet.getLong(1));
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro no driver Mysql " + ex.getMessage());
        }
        finally{
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return fornecedor;
    }

    @Override
    public void alterar(Object object) throws SQLException {
    Fornecedor fornecedor = (Fornecedor) object;
        String sql = "UPDATE fornecedor SET nome = ?, email = ?, telefone = ?, descricao = ? WHERE id = ? ";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, fornecedor.getNome());
            preparando.setString(2, fornecedor.getEmail());
            preparando.setString(3, fornecedor.getTelefone());
            preparando.setString(4, fornecedor.getDescricao());
            preparando.setLong(5, fornecedor.getId());
            preparando.executeUpdate();
          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FornecedorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);      
        }
    
    }    

    @Override
    public void excluir(Long id) throws SQLException {
        try {
            try {
                conexao = FabricaConexao.abrirConexao();
                preparando = conexao.prepareStatement("DELETE FROM fornecedor WHERE id = ?");
                preparando.setLong(1, id);
                preparando.executeUpdate();
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FornecedorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }


        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);      
        }
    }

    @Override
    public Object pesquisarPorID(Long id) throws SQLException {
        Fornecedor fornecedor = null;
        String consulta = "SELECT * FROM fornecedor WHERE id = ? ";       
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setLong(1, id);
            resultSet = preparando.executeQuery();
            if(resultSet.next()) {
                fornecedor = new Fornecedor(
                    id,
                    resultSet.getString("nome"),
                    resultSet.getString("email"),
                    resultSet.getString("telefone"),
                    resultSet.getString("descricao"),
                    resultSet.getDate("dataCadastro")
                );          
            }
                
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FornecedorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);          
        }
        return fornecedor;
    }

    @Override
    public List pesquisarPorNome(String nome) throws SQLException {
        String consulta = "SELECT * FROM fornecedor WHERE nome LIKE ?";
        List<Fornecedor> fornecedores = new ArrayList<>();
        Fornecedor fornecedor;
        try {
            conexao =  FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setString(1, "%" + nome + "%");
            resultSet = preparando.executeQuery();
            while(resultSet.next()) {
                fornecedor = new Fornecedor (
                    resultSet.getLong("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("email"),
                    resultSet.getString("telefone"),
                    resultSet.getString("descricao"),
                    resultSet.getDate("dataCadastro")
                );
                fornecedores.add(fornecedor);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FornecedorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);          

        }
        return fornecedores;
    }

    @Override
    public List pesquisarTodos() throws SQLException {
        String consulta = "SELECT * FROM fornecedor";
        List<Fornecedor> fornecedores = new ArrayList<>();
        Fornecedor fornecedor;
        try {
            conexao =  FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while(resultSet.next()) {
                fornecedor = new Fornecedor (
                    resultSet.getLong("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("email"),
                    resultSet.getString("telefone"),
                    resultSet.getString("descricao"),
                    resultSet.getDate("dataCadastro")
                );
                fornecedores.add(fornecedor);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FornecedorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);          
        }
        return fornecedores;
    }  
}