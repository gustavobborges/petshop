<%-- 
    Document   : contato
    Created on : 26/08/2020, 00:54:43
    Author     : gusta
--%>
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
            
            <c:if test="${mensagem != null}">
                <p>${sucesso}</p>
            </c:if>
            
            <form id="form-email" method="post" action="/SitePetshop/crud_fornecedor?cmd=salvar">
                <div class="div_flex">
                    <label>Nome: </label>
                    <input type="text" name="nome" required>
                </div>     
                <div class="div_flex">
                    <label>Email: </label>
                    <input type="text" name="email" required>
                </div>  
                <div class="div_flex">
                    <label>Telefone: </label>
                    <input type="text" name="telefone" required>
                </div>  
                <div class="div_flex">
                    <label>Descrição: </label>
                    <textarea name="descricao" required></textarea>
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