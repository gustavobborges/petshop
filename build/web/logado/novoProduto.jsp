<%-- 
    Document   : novoProduto
    Created on : 21/09/2020, 08:32:17
    Author     : gusta
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Petshop - Novo Produto</title>
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
                    <a href="/SitePetshop/logado/produto.jsp">Pesquisar</a>
                </li>
                <li>
                    <a href="/SitePetshop/logado/novoProduto.jsp">Novo</a>
                </li>
            </ul>            
        </div>
        
        <div id="sistema" class="espaco_topo div_borda">
            <h1>Novo Produto</h1>
            
            <c:if test="${msgSucesso != null}">
                <p class="mensagemSucesso">${msgSucesso}</p>
            </c:if>
                
            <c:if test="${msgErro != null}">
                <p class="mensagemErro">${msgErro}</p>
            </c:if>
            
            <form id="form_email" method="post" action="/SitePetshop/crud_produto?cmd=salvar">
                <input type="hidden" name="id" value="${produto.id}" >
                <div class="div_flex">
                    <label>Nome: </label>
                    <input type="text" name="nome" required value="${produto.nome}">
                </div>     
                <div class="div_flex">
                    <label>Descri��o: </label>
                    <input type="text" name="descricao" required value="${produto.descricao}">
                </div>  
                <div class="div_flex">
                    <label>Pre�o: </label>
                    <input type="text" name="preco" required value="${produto.preco}">
                </div>  
                <div class="div_flex">
                    <label>Estoque </label>
                    <input type="text" name="estoque" required value="${produto.estoque}">
                </div>
                <div class="div_flex">
                    <label>C�digo </label>
                    <input type="text" name="codigo" required value="${produto.codigo}">
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