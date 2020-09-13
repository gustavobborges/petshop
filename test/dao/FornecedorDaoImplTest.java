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
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import util.UtilTeste;

/**
 *
 * @author gusta
 */
public class FornecedorDaoImplTest {
    
    private Fornecedor fornecedor;
    private FornecedorDao fornecedorDao;
//    private String consulta;
    
    public FornecedorDaoImplTest() {
        fornecedorDao = new FornecedorDaoImpl();
    
    }

    /**
     * Test of salvar method, of class FornecedorDaoImpl.
     */
    
   @Test
    public void testSalvar() throws SQLException {
        System.out.println("salvar");
        
        fornecedor = new Fornecedor(null, "fornecedor" + UtilTeste.gerarCaracter(5), UtilTeste.gerarEmail(), UtilTeste.gerarTelefone(), "bla bla bla", new Date());
        
        try {  
            fornecedorDao.salvar(fornecedor);
            assertNotNull(fornecedor.getId());
        } catch (Exception ex) {
            fail ("Erro ao teste salvar " + ex.getMessage());
        }     
    }

    /**
     * Test of alterar method, of class FornecedorDaoImpl.
     */
//    @Test
    public void testAlterar() throws SQLException {
        System.out.println("alterar");
        buscarFornecedorBD();
        fornecedor.setNome("Fornecedor alterado!" + UtilTeste.gerarCaracter(5));
        
        try {
            fornecedorDao.alterar(fornecedor);
            Fornecedor fornecedorAlt = (Fornecedor) fornecedorDao.pesquisarPorID(fornecedor.getId());
            assertEquals(fornecedor.getNome(), fornecedorAlt.getNome());
         
        } catch (Exception ex) {
            fail("Erro no teste alterar " + ex.getMessage());
        }
    }

    /**
     * Test of excluir method, of class FornecedorDaoImpl.
     */
//    @Test
    public void testExcluir() throws SQLException {
        System.out.println("excluir");
        buscarFornecedorBD();
        fornecedorDao.excluir(fornecedor.getId());
        
        Fornecedor fornecedorExc = (Fornecedor) fornecedorDao.pesquisarPorID(fornecedor.getId());
        assertNull(fornecedorExc);

               
    }

    /**
     * Test of pesquisarPorID method, of class FornecedorDaoImpl.
     */
//    @Test
    public void testPesquisarPorID() throws SQLException {
        System.out.println("pesquisarPorID");
    }

    /**
     * Test of pesquisarPorNome method, of class FornecedorDaoImpl.
     */
//    @Test
    public void testPesquisarPorNome() throws SQLException {
        System.out.println("pesquisarPorNome");
        buscarFornecedorBD();
        List<Fornecedor> fornecedorPorNome = 
            fornecedorDao.pesquisarPorNome("fornecedor");
        assertTrue(!fornecedorPorNome.isEmpty());       
    }

    /**
     * Test of pesquisarTodos method, of class FornecedorDaoImpl.
     */
//    @Test
    public void testPesquisarTodos() throws SQLException {
        System.out.println("pesquisarTodos");
        buscarFornecedorBD();
        List<Fornecedor> fornecedores = fornecedorDao.pesquisarTodos();
        assertTrue(!fornecedores.isEmpty());     
    }
    
    public Fornecedor buscarFornecedorBD() {
        Connection conexao;
        PreparedStatement preparando;
        ResultSet resultSet;
        
        String consulta = "Select max(id) as id FROM fornecedor";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            resultSet.next();
            long id = resultSet.getLong("id");
            if(id != 0) {
                fornecedor = (Fornecedor)fornecedorDao.pesquisarPorID(id);
            } else {
                testSalvar();
            }
        } catch (Exception ex) {
            System.out.println("Erro no buscarFornecedorDB" + ex.getMessage());
        }
        
        return fornecedor;
    }
    
    
}
