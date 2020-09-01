/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author gusta
 */
public interface BaseDao {
    
    Object salvar(Object object) throws Exception;

    void alterar(Object object) throws Exception;
    
    void excluir(int id ) throws Exception;

    Object pesquisarPorID(Integer id) throws Exception;
    
    List pesquisarPorNome(String nome) throws Exception;
    
            
}
