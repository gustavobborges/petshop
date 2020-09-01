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
import java.util.List;

/**
 *
 * @author gusta
 */
public class FornecedorDaoImpl implements FornecedorDao {

    private Fornecedor fornecedor;
    private Connection conexao;
    private PreparedStatement preparando;
    private ResultSet resultSet;

    @Override
    public Object salvar(Object object) throws Exception {
        fornecedor = (Fornecedor) object;
        String sql = "INSERT INTO fornecedor(nome, email, telefone, descricao, dataCadastro)" +
                "VALUES(?, ?, ?, ?, ?)";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, fornecedor.getNome());
            preparando.setString(2, fornecedor.getEmail());
            preparando.setString(3, fornecedor.getTelefone());
            preparando.setString(4, fornecedor.getDescricao());
            preparando.setDate(5, new Date(fornecedor.getDataCadastro().getTime()));
            preparando.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Erro ao salvar fornecedor!" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
            
        }
        
        return null;
    }

    @Override
    public void alterar(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object pesquisarPorID(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List pesquisarPorNome(String nome) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List pesquisarTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}