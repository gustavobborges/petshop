/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class ProdutoDaoImpl implements ProdutoDao {

    private Connection conexao;
    private PreparedStatement preparando;
    private ResultSet resultSet;

    @Override
    public Object salvar(Object object) throws SQLException {
        Produto produto = (Produto) object;
        String sql = "INSERT INTO produto(nome, descricao, preco, estoque, codigo)"
                   + " VALUES(?, ?, ?, ?, ?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, produto.getNome());
            preparando.setString(2, produto.getDescricao());
            preparando.setString(3, produto.getPreco());
            preparando.setString(4, produto.getEstoque());
            preparando.setString(5, produto.getCodigo());
            preparando.executeUpdate();
            resultSet = preparando.getGeneratedKeys();
            resultSet.next();
            produto.setId(resultSet.getLong(1));
        }
        finally{
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return produto;
    }

    @Override
    public void alterar(Object object) throws SQLException {
    Produto produto = (Produto) object;
        String sql = "UPDATE produto SET nome = ?, descricao = ?, preco = ?, estoque = ?, codigo = ? WHERE id = ? ";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, produto.getNome());
            preparando.setString(2, produto.getDescricao());
            preparando.setString(3, produto.getPreco());
            preparando.setString(4, produto.getEstoque());
            preparando.setString(5, produto.getCodigo());
            preparando.setLong(6, produto.getId());
            preparando.executeUpdate();
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);      
        }
    }    

    @Override
    public void excluir(Long id) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement("DELETE FROM produto WHERE id = ?");
            preparando.setLong(1, id);
            preparando.executeUpdate();
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);      
        }
    }

    @Override
    public Object pesquisarPorID(Long id) throws SQLException {
        Produto produto = null;
        String consulta = "SELECT * FROM produto WHERE id = ? ";       
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setLong(1, id);
            resultSet = preparando.executeQuery();
            if(resultSet.next()) {
                produto = new Produto(
                    id,
                    resultSet.getString("nome"),
                    resultSet.getString("descricao"),
                    resultSet.getString("preco"),
                    resultSet.getString("estoque"),
                    resultSet.getString("codigo")
                );          
            }
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);          
        }
        return produto;
    }

    @Override
    public List pesquisarPorNome(String nome) throws SQLException {
        String consulta = "SELECT * FROM produto WHERE nome LIKE ?";
        List<Produto> produtos = new ArrayList<>();
        Produto produto;
        try {
            conexao =  FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setString(1, "%" + nome + "%");
            resultSet = preparando.executeQuery();
            while(resultSet.next()) {
                produto = new Produto (
                    resultSet.getLong("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("descricao"),
                    resultSet.getString("preco"),
                    resultSet.getString("estoque"),
                    resultSet.getString("codigo")
                );
                produtos.add(produto);
            }
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);          

        }
        return produtos;
    }

    @Override
    public List<Produto> pesquisarTodo() throws SQLException {
        String consulta = "SELECT * FROM produto";
        List<Produto> produtos = new ArrayList<>();
        Produto produto;
        try {
            conexao =  FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while(resultSet.next()) {
                produto = new Produto (
                    resultSet.getLong("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("descricao"),
                    resultSet.getString("preco"),
                    resultSet.getString("estoque"),
                    resultSet.getString("codigo")
                );
                produtos.add(produto);
            }
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);          
        }
        return produtos;
    }  

}