<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: pierr
  Date: 21/02/2022
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/connexion.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <title>Connexion - Médiathèques de la vallée de San Diego</title>
    <link rel="icon" href="${pageContext.request.contextPath}/view/content/logo_favi.png">
</head>
<body style="background-color: #E8E1E1; ">
<div class="hero is-fullheight columns is-flex is-centered is-vcentered">
    <div class="hero-head"></div>
    <div class="hero-body has-text-centered column">
        <!-- FORM -->
        <form class="box container section has-background-white-ter" action="${pageContext.request.contextPath}/Connexion"
              method="post"
              style="border-radius: 5px;">
            <img alt="Logo" src="${pageContext.request.contextPath}/view/content/logo_small.png">
            <h3 class="subtitle">Authentification</h3>
            <div class="field">
                <label class="label">Identifiant</label>
                <div class="control">
                    <input class="input" type="text" placeholder="Tapez votre identifiant" name="login">
                </div>
            </div>

            <div class="field">
                <label class="label">Mot de passe</label>
                <div class="control">
                    <input class="input" type="password" placeholder="Tapez votre mot de passe" name="password">
                </div>
            </div>

            <!-- SIGN IN -->
            <div class="field">
                <p class="control">
                    <button class="button is-success" type="submit">
                        Connexion
                    </button>
                </p>
            </div>
        </form>
    </div>
    <div class="hero-foot"></div>
</div>
<%
    if (!(request.getAttribute("loginfailed") == null))
        if (request.getAttribute("loginfailed").equals("failed")) {
%>
<div id="modal-js-example" class="modal is-active">
    <div class="modal-background"></div>
    <div class="modal-content">
        <div class="box">
            <%
                if (!(request.getAttribute("notConnected") == null)) {
                    if(request.getAttribute("notConnected").equals("true")){
            %>
            <p class="has-text-info-dark has-text-centered">Veuillez vous connecter pour accéder à la médiathèque</p>
            <% }}else{%>
            <p class="has-text-danger-dark has-text-centered">Le nom d'utilisateur ou le mot de passe est incorrect</p>
            <%}%>
        </div>
    </div>
    <button class="modal-close is-large" aria-label="close"></button>
</div>
<%}%>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        // Functions to open and close a modal
        function openModal($el) {
            $el.classList.add('is-active');
        }

        function closeModal($el) {
            $el.classList.remove('is-active');
        }

        // Add a click event on buttons to open a specific modal
        (document.querySelectorAll('.js-modal-trigger') || []).forEach(($trigger) => {
            const modal = $trigger.dataset.target;
            const $target = document.getElementById(modal);
            console.log($target);

            $trigger.addEventListener('click', () => {
                openModal($target);
            });
        });

        // Add a click event on various child elements to close the parent modal
        (document.querySelectorAll('.modal-background, .modal-close, .modal-card-head .delete, .modal-card-foot .button') || []).forEach(($close) => {
            const $target = $close.closest('.modal');

            $close.addEventListener('click', () => {
                closeModal($target);
            });
        });
    });
</script>

</body>
</html>
