/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ProdutoDaoImpl;
import dao.ProdutoDao;
import entidade.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gusta
 */
@WebServlet(name = "ProdutoControle", urlPatterns = {"/crud_produto"})
public class ProdutoControle extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;   
    private ProdutoDao produtoDao;
    private RequestDispatcher rd;
    
    protected void processRequest() throws ServletException, IOException {   
        String comando = request.getParameter("cmd");
        
        switch(comando) {
            case "pesquisarPorNome" :
                consultarPorNome();
                break;
                
            case "salvar" :
                novo();
                break;
                
            case "excluir" :
                excluir();
                break;
                
            case "alterar" :
                alterar();
                break;
              
        }
        rd.forward(request, response);
    }
    
    private void alterar() {
        Long id = Long.parseLong(request.getParameter("idTela"));
        produtoDao = new ProdutoDaoImpl();
        
        try {
            Produto forn = (Produto) produtoDao.pesquisarPorID(id);
            request.setAttribute("produto", forn);

        } catch (Exception e) {
            request.setAttribute("msgErro", "Erro ao tentar pesquisar!" + e.getMessage());
        }
        rd = request.getRequestDispatcher("logado/novoProduto.jsp");
    }
    
    private void excluir() {
       Long id = Long.parseLong(request.getParameter("idTela"));
       produtoDao = new ProdutoDaoImpl();
       try {
           produtoDao.excluir(id);
           request.setAttribute("msgSucesso", "Excluído com sucesso!");
       } catch (Exception e) {
           request.setAttribute("msgErro", "Erro ao tentar excluir!");
       }
        rd = request.getRequestDispatcher("logado/novoProduto.jsp");
    }
    
    private void novo() {
        Produto produto = new Produto();
        
        String id = request.getParameter("id");

        produto.setNome(request.getParameter("nome"));
        produto.setDescricao(request.getParameter("descricao"));
        produto.setPreco(request.getParameter("preco"));
        produto.setEstoque(request.getParameter("estoque"));
        produto.setCodigo(request.getParameter("codigo"));

        try {
            produtoDao = new ProdutoDaoImpl();
            if (id != null) {
                produto.setId(Long.parseLong(id));
                produtoDao.alterar(produto);
                request.setAttribute("msgSucesso", "Alterado com sucesso!");

            } else {
                produtoDao.salvar(produto);
                request.setAttribute("msgSucesso", "Salvo com sucesso!");
            }
        } catch (Exception e) {
            request.setAttribute("msgErro", "Erro ao salvar!");
        }
        rd = request.getRequestDispatcher("logado/novoProduto.jsp");
    }
    
    private void consultarPorNome() {
        String nome = request.getParameter("nome");
        try {
            produtoDao = new ProdutoDaoImpl();
            List<Produto> produtos = produtoDao.pesquisarPorNome(nome);
            
            if(produtos.isEmpty()) {
                request.setAttribute("msgAlerta", "Não foi encontrado nenhum registro com esse nome!");
                request.setAttribute("produtos", null);
                produtos = null;
            } else {
                request.setAttribute("produtos", produtos);
            }          
                                        
        } catch (Exception e) {
            request.setAttribute("msgErro", "Erro ao pesquisa por nome!" + e.getMessage());
        }
        rd = request.getRequestDispatcher("logado/produto.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        processRequest();
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        processRequest();
        }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
