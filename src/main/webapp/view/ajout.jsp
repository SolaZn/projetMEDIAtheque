<%--
  Created by IntelliJ IDEA.
  User: pierr
  Date: 21/02/2022
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter</title>
</head>
<body>
  
  <section class="section login">
    <!-- TITLE -->
    <h2 class="section__title"></h2>
    <div class="login__container container">
        <div class="login__content">

            <!-- FORM -->
            <form action="./index.jsp?controle=document&action=ajouter" method="post" class="login__create" id="login-in">
                <h3 class="login__title">Ajouter un document</h3>
                <div class="login__box">
                    <i class="bx bxs-car login__icon"></i>
                    <input type="text" name="nom" value="<%= nom %>" placeholder="Nom" class="login__input">
                </div>
                <div class="login__box">
                    <i class="bx bxs-gas-pump login__icon"></i>
                    <input type="text" name="type" value="<%= type %>" placeholder="Type" class="login__input">
                </div>
                <div class="login__box">
                    <i class="bx bx-stats login__icon"></i>
                    <input type="text" name="details" value="<%= details %>" placeholder="Détails" class="login__input">
                </div>
                <div class="login__box">
                    <i class="bx bx-link-alt login__icon"></i>
                    <input type="text" name="img" value="<%= img %>" placeholder="Image URL" class="login__input">
                </div>
                
                
                <!-- ADD -->
                <input type="submit" value="Ajouter" class="button login__button"></input>
            </form>
        </div>
    </div>
</section>

</body>
</html>
