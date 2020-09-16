<%-- 
    Document   : contato
    Created on : 26/08/2020, 00:54:43
    Author     : gusta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Petshop - Contato</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div id="principal">
        <div id="banner" class="div_borda escopo_topo">
            <img src="imagem/dogs-banner.jpg" alt="petshop">
        </div>
        <div id="menu" class="div_borda escopo_topo">
            <ul>
                <li>
                    <a href="index.html">HOME</a>
                </li>
                <li>
                    <a href="produto.html">PRODUTO</a>
                </li>
                <li>
                    <a href="cliente.html">CLIENTE</a>
                </li>
                <li>
                    <a href="contato.jsp">CONTATO</a>
                </li>
            </ul>
        </div>

        <div id= "lado_esquerdo" class="escopo_topo div_borda">
            <h1>Formulário</h1>
            
            <span>${sucesso}</span>
            
            
            <form id="form-email" method="post" action="testeFormulario">
                <div class="div_flex">
                    <label>Email: </label>
                    <input type="email" name="email" required>
                </div>
                
                <div class="div_flex">
                    <label>Senha: </label>
                    <input type="password" name="senha" required>
                </div>
                
                <div class="div_flex">
                    <label>Cadastro: </label>
                    <input type="date" name="data" required>
                </div>
                
                <div class="div_flex">
                    <label>Tecnologias: </label>
                    <input id="check" type="checkbox" name="linguagens" value="php"/>Php
                    <input id="check" type="checkbox" name="linguagens" value="javascript"/>Javascript
                    <input id="check" type="checkbox" name="linguagens" value="java"/>Java
                    <input id="check" type="checkbox" name="linguagens" value="python"/>Python  
                </div>
                
                <div class="div_flex">
                    <label>Sexo: </label>
                    <input id="check" type="radio" name="sexo" value="Feminino">Feminino
                    <input id="check" type="radio" name="sexo" value="Masculino">Masculino    
                </div>

                <div class="div_flex">
                    <input class="botao botao_esquerda" type="submit" value="Salvar">
                    <input class="botao" type="reset" value="Limpar">
                </div>
            </form>
            


        </div>
        <div id="banner_2" class="div_borda escopo_topo">
            <img src="" alt="">
        </div>
        <div id= "rodape" class="div_borda escopo_topo"></div>
    </div>
</body>
</html>