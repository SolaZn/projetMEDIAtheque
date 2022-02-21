<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: pierr
  Date: 21/02/2022
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String login = "";
    String mdp = "";
%>
<html>
  <section class="section login">
    <!-- TITLE -->
    <h2 class="section__title">Connexion</h2>
    <div class="login__container container">
        <div class="login__content">
            <div class="login__img">
                <img src="./vue/img/login-img.svg" alt="login">
            </div>
            <!-- FORM -->
            <div class="login__form">
                <!--<form action="./index.php?controle=utilisateur&action=ident" method="post" class="login__register" id="login-in">-->
                    <h3 class="login__title">Authentification</h3>
                    <div class="login__box">
                        <i class="bx bxs-user login__icon"></i>
                        <input type="text" name="login" value="<%= login %>" placeholder="Identifiant" class="login__input">
                    </div>
                    <div class="login__box">
                        <i class="bx bxs-lock login__icon"></i>
                        <input type="password" name="mdp" value="<%= mdp %>" placeholder="Mot de passe" class="login__input" id="password">
                        <i class='bx bxs-show login__togglePassword' id="toggle-password"></i>
                    </div>
                
                    <!-- SIGN IN -->
                    <input type="submit" value="Se connecter" class="button login__button"/>
                <!--</form>-->
            </div>
            <% List<String> utilisateurs = (List<String>) request.getAttribute("users");
                for(String user : utilisateurs){%>
            <%=user%>
            <%}%>
        </div>
    </div>
</section>
</html>
