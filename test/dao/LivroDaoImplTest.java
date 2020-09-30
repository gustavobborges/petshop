/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Fornecedor;
import entidade.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
import util.UtilTeste;

/**
 *
 * @author gusta
 */
public class LivroDaoImplTest {
    
    private Livro livro;
    private LivroDao livroDao;
    
    public LivroDaoImplTest() {
        livroDao = new LivroDaoImpl();
    }

    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        
        livro = new Livro(null, UtilTeste.gerarCaracter(10), UtilTeste.gerarCaracter(20), "1999", UtilTeste.gerarCaracter(8), UtilTeste.gerarCaracter(8));
        
        try {  
            livroDao.salvar(livro);
            assertNotNull(livro.getId());
        } catch (Exception ex) {
            fail ("Erro ao teste salvar " + ex.getMessage());
        }     
    }
    
    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarLivroDB();
        livro.setNome("Livro alterado!" + UtilTeste.gerarCaracter(5));
        
        try {
            livroDao.alterar(livro);
            Livro livroAlt = (Livro) livroDao.pesquisarPorID(livro.getId());
            assertEquals(livro.getNome(), livroAlt.getNome());
         
        } catch (Exception ex) {
            fail("Erro no teste alterar " + ex.getMessage());
        }
    }

    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarLivroDB();
        livroDao.excluir(livro.getId());
        
        Fornecedor livroExc = (Fornecedor) livroDao.pesquisarPorID(livro.getId());
        assertNull(livroExc);           
    }

    @Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisarPorNome");
        buscarLivroDB();
        List<Livro> livroPorNome = 
            livroDao.pesquisarPorNome("livro");
        assertTrue(!livroPorNome.isEmpty());       
    }
    
//    @Test
    public void testPesquisarPorID() throws Exception {

    }
    
    @Test
    public void testPesquisarTodos() throws Exception {
        System.out.println("pesquisarTodos");
        buscarLivroDB();
        List<Livro> livros = livroDao.pesquisarTodos();
        assertTrue(!livros.isEmpty());     
    }
    
    

    public Livro buscarLivroDB() {
        Connection conexao;
        PreparedStatement preparando;
        ResultSet resultSet;
        
        String consulta = "Select max(id) as id FROM livro";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            resultSet.next();
            long id = resultSet.getLong("id");
            if(id != 0) {
                livro = (Livro)livroDao.pesquisarPorID(id);
            } else {
                testSalvar();
            }
        } catch (Exception ex) {
            System.out.println("Erro no buscarLivroDB" + ex.getMessage());
        }
        
        return livro;
    }


}
