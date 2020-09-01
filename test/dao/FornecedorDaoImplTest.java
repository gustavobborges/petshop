/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Fornecedor;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gusta
 */
public class FornecedorDaoImplTest {
    
    private Fornecedor fornecedor;
    private FornecedorDao fornecedorDao;
    
    public FornecedorDaoImplTest() {
        fornecedorDao = new FornecedorDaoImpl();
    
    }

    /**
     * Test of salvar method, of class FornecedorDaoImpl.
     */
    
//    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        
        fornecedor = new Fornecedor(null, "Teste fornecedor1", "gustavo@gmail.com", "(48)3222-5500", "bla bla bla", new Date());
        
        fornecedorDao.salvar(fornecedor);
    }

    /**
     * Test of alterar method, of class FornecedorDaoImpl.
     */
    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
    }

    /**
     * Test of excluir method, of class FornecedorDaoImpl.
     */
//    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
    }

    /**
     * Test of pesquisarPorID method, of class FornecedorDaoImpl.
     */
//    @Test
    public void testPesquisarPorID() throws Exception {
        System.out.println("pesquisarPorID");
    }

    /**
     * Test of pesquisarPorNome method, of class FornecedorDaoImpl.
     */
//    @Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisarPorNome");
    }

    /**
     * Test of pesquisarTodos method, of class FornecedorDaoImpl.
     */
//    @Test;
    public void testPesquisarTodos() throws Exception {
        System.out.println("pesquisarTodos");
    }
    
}
