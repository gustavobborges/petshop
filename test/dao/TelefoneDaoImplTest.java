/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Telefone;
import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;
import util.UtilTeste;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dao.TelefoneDaoImpl;
import java.util.List;

/**
 *
 * @author gusta
 */
public class TelefoneDaoImplTest {
    
    private TelefoneDao telefoneDao;
    private Telefone telefone;

    
    
    public TelefoneDaoImplTest() {
        telefoneDao = new TelefoneDaoImpl();
    }

    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        telefone = new Telefone(
                null,
                UtilTeste.gerarTelefoneSemDDD(),
                "48",
                "Operadora " + UtilTeste.gerarCaracter(5) ,
                "Contato " + UtilTeste.gerarCaracter(5)
        );       
        telefoneDao.salvar(telefone);
        assertNotNull(telefone.getId());       
    }

    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarTelefoneDB();
        telefone.setNumero(UtilTeste.gerarTelefoneSemDDD());
        telefone.setOperadora("Operadora " + UtilTeste.gerarCaracter(5));
        telefoneDao.alterar(telefone); 
        Telefone telAlterado = (Telefone) telefoneDao.pesquisarPorID(telefone.getId());
        assertEquals(telefone.getNumero(), telAlterado.getNumero());
        assertEquals(telefone.getOperadora(), telAlterado.getOperadora());
    }

    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarTelefoneDB();
        telefoneDao.excluir(telefone.getId());
        Telefone telExcluido = (Telefone) telefoneDao.pesquisarPorID(telefone.getId());
        assertNull(telExcluido);             
    }

    @Test
    public void testPesquisarPorID() throws Exception {
        System.out.println("pesquisarPorID");
      
    }

    @Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisarPorNome");
     
    }

    @Test
    public void testPesquisarTodos() throws Exception {
        System.out.println("pesquisarTodos");
        buscarTelefoneDB();
        List<Telefone> telefones = telefoneDao.pesquisarTodos();
        assertTrue(!telefones.isEmpty());
      
    }

    @Test
    public void testPesquisarPorTelefone() throws Exception {
        System.out.println("pesquisarPorTelefone");
        buscarTelefoneDB();
        List<Telefone> telefones = telefoneDao.pesquisarPorTelefone(telefone.getNumero());
        assertTrue(!telefones.isEmpty());
              
    }
 
    public Telefone buscarTelefoneDB() {
        Connection conexao;
        PreparedStatement preparando;
        ResultSet resultSet;
        
        String consulta = "Select max(id) as id FROM telefone";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            resultSet.next();
            long id = resultSet.getLong("id");
            if(id != 0) {
                 telefone = (Telefone)telefoneDao.pesquisarPorID(id);
            } else {
                testSalvar();
            }
        } catch (Exception ex) {
            System.out.println("Erro no buscarTelefoneDB" + ex.getMessage());
        }
        
        return telefone;
    }
}
