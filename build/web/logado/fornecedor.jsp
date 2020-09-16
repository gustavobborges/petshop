<%-- 
    Document   : contato
    Created on : 26/08/2020, 00:54:43
    Author     : gusta
--%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Petshop - Contato</title>
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
                    <a href="/SitePetshop/logado/fornecedor.jsp">Pesquisar</a>
                </li>
                <li>
                    <a href="/SitePetshop/logado/novoFornecedor.jsp">Novo</a>
                </li>
            </ul>            
        </div>
        
        <div id="sistema" class="espaco_topo div_borda">
            <h1>Fornecedor</h1>
            
            <c:if test="${msgAlerta != null}">
                <p class="mensagemAlerta">${msgAlerta}</p>
            </c:if>
                
            <c:if test="${msgSucesso != null}">
                <p class="mensagemSucesso">${msgSucesso}</p>
            </c:if>
            
            <c:if test="${msgErro != null}">
                <p class="mensagemErro">${msgErro}</p>
            </c:if>
                
            <form id="form_email" method="post" action="/SitePetshop/crud_fornecedor?cmd=pesquisarPorNome">
                <div class="div_flex">
                    <label>Nome: </label>
                    <input type="text" name="nome" required>
                    <input class="botao botao_pesquisa" value="Pesquisar" type="submit"/>
                </div>
           
                <c:if test="${fornecedores != null}">
                    <table class="table_pesquisa">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Cadastro</th>
                                <th>Telefone</th>
                                <th>Email</th>
                                <th style="width: 110px;">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${fornecedores}" var="fornecedor">
                                <tr>
                                    <td>${fornecedor.nome}</td>
                                    <td>
                                        <f:formatDate value="${fornecedor.dataCadastro}" pattern="dd/MM/YYYY"/>
                                    </td>
                                    <td>${fornecedor.telefone}</td>
                                    <td>${fornecedor.email}</td>
                                    <td>
                                        <a href="/SitePetshop/crud_fornecedor?cmd=alterar&idTela=${fornecedor.id}">
                                            <img class="espaco_img" src="/SitePetshop/imagem/lapis.png" title="Alterar"/>
                                        </a>
                                        
                                        <a href="/SitePetshop/crud_fornecedor?cmd=excluir&idTela=${fornecedor.id}">
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