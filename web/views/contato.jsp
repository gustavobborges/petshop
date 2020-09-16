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
            <h1>Fale Conosco</h1>
            
            <span>${sucesso}</span>
            
            
            <form id="form-email" method="post" action="site_email">
                <div class="div_flex">
                    <label>Nome: </label>
                    <input type="text" name="name" required>
                </div>

                <div class="div_flex">
                    <label>Email: </label>
                    <input type="email" name="email" required>
                </div>

                <div class="div_flex">
                    <label>Assunto: </label>
                    <select name="assunto">
                        <option value="financeiro">Financeiro</option>
                        <option value="suporte">Suporte Técnico</option>
                        <option value="venda">Vendas</option>
                    </select>
                </div>

                <div class="div_flex">
                    <label>Descrição: </label>
                    <textarea id="email_textarea" name="mensagem" required></textarea>
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