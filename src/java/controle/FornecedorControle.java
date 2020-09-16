/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.FornecedorDao;
import dao.FornecedorDaoImpl;
import entidade.Fornecedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
@WebServlet(name = "FornecedorControle", urlPatterns = {"/crud_fornecedor"})
public class FornecedorControle extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;   
    private FornecedorDao fornecedorDao;
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
        }
        rd.forward(request, response);
    }
    
    private void novo() {
        Fornecedor fornecedor = new Fornecedor();
        
        String id = request.getParameter("id");

        fornecedor.setNome(request.getParameter("nome"));
        fornecedor.setEmail(request.getParameter("email"));
        fornecedor.setTelefone(request.getParameter("telefone"));
        fornecedor.setDescricao(request.getParameter("descricao"));
        fornecedor.setDataCadastro(new Date());
        try {
            fornecedorDao = new FornecedorDaoImpl();
            if (id != null) {
                
            } else {
                fornecedorDao.salvar(fornecedor);
                request.setAttribute("mensagem", "Salvo com sucesso!");
            }
        } catch (Exception e) {
            request.setAttribute("mensagem", "Erro ao salvar!");
        }
        rd = request.getRequestDispatcher("logado/novoFornecedor.jsp");


    }
    
    private void consultarPorNome() {
        String nome = request.getParameter("nome");
        try {
            fornecedorDao = new FornecedorDaoImpl();
            List<Fornecedor> forns = fornecedorDao.pesquisarPorNome(nome);
            request.setAttribute("fornecedores", forns);
            rd = request.getRequestDispatcher("logado/fornecedor.jsp");
                                        
        } catch (Exception e) {
        }
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
