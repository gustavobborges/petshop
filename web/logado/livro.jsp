<%-- 
    Document   : livro
    Created on : 30/09/2020, 08:37:16
    Author     : gusta
--%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Petshop - Livro</title>
        <link rel="stylesheet" href="/SitePetshop/css/style.css">
    </head>
<body>
    <div id="principal">
        <div id="banner" class="div_borda escopo_topo">
            <img src="/SitePetshop/imagem/dogs-banner.jpg" alt="petshop">
        </div>
        
        <%@include file="menu.html" %>
        
        <div id="menu_lateral" class="div_borda escopo_topo">           
            <ul>
                <li>
                    <a href="/SitePetshop/logado/livro.jsp">Pesquisar</a>
                </li>
                <li>
                    <a href="/SitePetshop/logado/novoLivro.jsp">Novo</a>
                </li>
            </ul>            
        </div>
        
        <div id="sistema" class="espaco_topo div_borda">
            <h1>LIVRO</h1>
            
            <c:if test="${msgAlerta != null}">
                <p class="mensagemAlerta">${msgAlerta}</p>
            </c:if>
                
            <c:if test="${msgSucesso != null}">
                <p class="mensagemSucesso">${msgSucesso}</p>
            </c:if>
            
            <c:if test="${msgErro != null}">
                <p class="mensagemErro">${msgErro}</p>
            </c:if>
                
            <form id="form_email" method="post" action="/SitePetshop/crud_livro?cmd=pesquisarPorNome">
                <div class="div_flex">
                    <label>Nome: </label>
                    <input type="text" name="nome" required>
                    <input class="botao botao_pesquisa" value="Pesquisar" type="submit"/>
                </div>
           
                <c:if test="${livros != null}">
                    <table class="table_pesquisa">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Descrição</th>
                                <th>Ano Edição</th>
                                <th>Autor</th>
                                <th>Editora</th>
                                <th style="width: 110px;">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${livros}" var="livro">
                                <tr>
                                    <td>${livro.nome}</td>
                                    <td>${livro.descricao}</td>
                                    <td>${livro.anoEdicao}</td>
                                    <td>${livro.autor}</td>
                                    <td>${livro.editora}</td>
                                    <td>
                                        <a href="/SitePetshop/crud_livro?cmd=alterar&idTela=${livro.id}">
                                            <img class="espaco_img" src="/SitePetshop/imagem/lapis.png" title="Alterar"/>
                                        </a>
                                        
                                        <a href="/SitePetshop/crud_livro?cmd=excluir&idTela=${livro.id}">
                                            <img src="/SitePetshop/imagem/lixeira.png" title="Excluir"/>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>                  
                </c:if> 
            </form>
        </div>
        
       
        <div id= "rodape" class="div_borda escopo_topo"></div>
    </div>
</body>
</html>