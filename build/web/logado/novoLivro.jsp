<%-- 
    Document   : novoLivro
    Created on : 30/09/2020, 08:43:08
    Author     : gusta
--%>

--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Petshop - Novo Livro</title>
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
            
            <c:if test="${msgSucesso != null}">
                <p class="mensagemSucesso">${msgSucesso}</p>
            </c:if>
                
            <c:if test="${msgErro != null}">
                <p class="mensagemErro">${msgErro}</p>
            </c:if>
            
            <form id="form_email" method="post" action="/SitePetshop/crud_livro?cmd=salvar">
                <input type="hidden" name="id" value="${livro.id}" >
                <div class="div_flex">
                    <label>Nome: </label>
                    <input type="text" name="nome" value="${livro.nome}">
                </div>     
                <div class="div_flex">
                    <label>Ano da Edição: </label>
                    <input type="text" name="anoEdicao" value="${livro.anoEdicao}">
                </div>   
                <div class="div_flex">
                    <label>Autor: </label>
                    <input type="text" name="autor" value="${livro.autor}">
                </div>  
                <div class="div_flex">
                    <label>Editora: </label>
                    <input type="text" name="editora" value="${livro.editora}">
                </div>  
                <div class="div_flex">
                    <label>Descrição: </label>
                    <textarea name="descricao" value="${livro.descricao}" required></textarea>
                </div>               
                <div class="div_flex">
                    <input class="botao botao_esquerda" type="submit" value="Salvar">
                    <input class="botao" type="reset" value="Limpar">
                </div>
                
            </form>
        </div>
        
       
        <div id= "rodape" class="div_borda escopo_topo"></div>
    </div>
</body>
</html>