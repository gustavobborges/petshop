/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Livro;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface LivroDao extends BaseDao {
      
    List pesquisarTodos() throws SQLException;
    
    List pesquisarPorNome(String nome) throws SQLException;
    
}