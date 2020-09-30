/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.LivroDaoImpl;
import dao.LivroDao;
import entidade.Livro;
import java.io.IOException;
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
@WebServlet(name = "LivroControle", urlPatterns = {"/crud_livro"})
public class LivroControle extends HttpServlet {
    
    private HttpServletRequest request;
    private HttpServletResponse response;   
    private LivroDao livroDao;
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
        livroDao = new LivroDaoImpl();
        
        try {
            Livro livro = (Livro) livroDao.pesquisarPorID(id);
            request.setAttribute("livro", livro);

        } catch (Exception e) {
            request.setAttribute("msgErro", "Erro ao tentar pesquisar!" + e.getMessage());
        }
        rd = request.getRequestDispatcher("logado/novoLivro.jsp");
    }
    
    private void excluir() {
       Long id = Long.parseLong(request.getParameter("idTela"));
       livroDao = new LivroDaoImpl();
       try {
           livroDao.excluir(id);
           request.setAttribute("msgSucesso", "Excluído com sucesso!");
       } catch (Exception e) {
           request.setAttribute("msgErro", "Erro ao tentar excluir!");
       }
        rd = request.getRequestDispatcher("logado/novoLivro.jsp");
    }
    
    private void novo() {
        Livro livro = new Livro();
        
        String id = request.getParameter("id");

        livro.setNome(request.getParameter("nome"));
        livro.setDescricao(request.getParameter("descricao"));
        livro.setAnoEdicao(request.getParameter("anoEdicao"));
        livro.setAutor(request.getParameter("autor"));
        livro.setEditora(request.getParameter("editora"));

        try {
            livroDao = new LivroDaoImpl();
            if (!id.equals("")) {
                livro.setId(Long.parseLong(id));
                livroDao.alterar(livro);
                request.setAttribute("msgSucesso", "Alterado com sucesso!");

            } else {
                livroDao.salvar(livro);
                request.setAttribute("msgSucesso", "Salvo com sucesso!");
            }
        } catch (Exception e) {
            request.setAttribute("msgErro", "Erro ao salvar!");
        }
        rd = request.getRequestDispatcher("logado/novoLivro.jsp");


    }
    
    private void consultarPorNome() {
        String nome = request.getParameter("nome");
        try {
            livroDao = new LivroDaoImpl();
            List<Livro> livros = livroDao.pesquisarPorNome(nome);
            
            if(livros.isEmpty()) {
                request.setAttribute("msgAlerta", "Não foi encontrado nenhum registro com esse nome!");
                request.setAttribute("livros", null);
                livros = null;
            } else {
                request.setAttribute("livros", livros);
            }          
                                        
        } catch (Exception e) {
            request.setAttribute("msgErro", "Erro ao pesquisa por nome!" + e.getMessage());
        }
        rd = request.getRequestDispatcher("logado/livro.jsp");

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