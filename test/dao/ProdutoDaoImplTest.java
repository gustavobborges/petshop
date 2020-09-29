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
import java.time.LocalDate;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import util.UtilTeste;

/**
 *
 * @author gusta
 */
public class ProdutoDaoImplTest {
    
    private Produto produto;
    private ProdutoDao produtoDao;
    
    public ProdutoDaoImplTest() {
        produtoDao = new ProdutoDaoImpl();
    }

    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        
        produto = new Produto(null, UtilTeste.gerarCaracter(10), UtilTeste.gerarCaracter(10), UtilTeste.gerarPreco(), UtilTeste.gerarNumero(2), UtilTeste.gerarCaracter(5));
        
        try {  
            produtoDao.salvar(produto);
            assertNotNull(produto.getId());
        } catch (Exception ex) {
            fail ("Erro ao teste salvar " + ex.getMessage());
        }     
    }

    @Test
    public void testAlterar() throws Exception {
     System.out.println("alterar");
        buscarProdutoBD();
        produto.setNome("Produto alterado!" + UtilTeste.gerarCaracter(5));
        
        try {
            produtoDao.alterar(produto);
            Produto produtoAlt = (Produto) produtoDao.pesquisarPorID(produto.getId());
            assertEquals(produto.getNome(), produtoAlt.getNome());
         
        } catch (Exception ex) {
            fail("Erro no teste alterar " + ex.getMessage());
        }
    }

    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarProdutoBD();
        produtoDao.excluir(produto.getId());
        
        Produto produtoExc = (Produto) produtoDao.pesquisarPorID(produto.getId());
        assertNull(produtoExc);

    }
    
//    @Test
    public void testPesquisarPorID() throws Exception {
       
    }
//
    @Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisarPorNome");
        buscarProdutoBD();
        List<Produto> produtoPorNome = 
            produtoDao.pesquisarPorNome(produto.getNome());
        assertTrue(!produtoPorNome.isEmpty());  
    }

    @Test
    public void testPesquisarTodos() throws Exception {
        System.out.println("pesquisarTodos");
        buscarProdutoBD();
        List<Produto> produtos = produtoDao.pesquisarTodos();
        assertTrue(!produtos.isEmpty());     
    }
    
    public Produto buscarProdutoBD() {
    Connection conexao;
    PreparedStatement preparando;
    ResultSet resultSet;
    String cogigo= "";
    LocalDate localDateNovo = LocalDate.now();

    String consulta = "Select max(id) as id FROM produto";

    try {
        conexao = FabricaConexao.abrirConexao();
        preparando = conexao.prepareStatement(consulta);
        resultSet = preparando.executeQuery();
        resultSet.next();
        long id = resultSet.getLong("id");
        if(id != 0) {
            produto = (Produto)produtoDao.pesquisarPorID(id);
        } else {
            testSalvar();
        }
    } catch (Exception ex) {
        System.out.println("Erro no buscarProdutoDB" + ex.getMessage());
    }

    return produto;
    }
    
}
