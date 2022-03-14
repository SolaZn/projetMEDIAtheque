<%@ page import="mediatek2022.Document" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Anthony
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <script src="https://kit.fontawesome.com/d9b9925bfe.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/connexion.css">
    <title>Ajout d'un document - Médiathèques de la vallée de San Diego</title>
    <link rel="icon" href="${pageContext.request.contextPath}/view/content/logo_no_transparency.png">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body style="background-color: hsl(171, 100%, 41%)">
<div class="is-warning is-large is-fullheight-with-navbar">
    <div class="hero-head" style="background-color: #E8E1E1;">
        <nav class="navbar is-spaced">
            <div class="container">
                <div class="navbar-brand">
                    <img src="${pageContext.request.contextPath}/view/content/logo_small.png" alt="Logo">
                </div>
                <div class="navbar-item has-text-grey-dark" id="clock">
                </div>
            </div>
        </nav>
    </div>

    <div class="section is-success">
        <div class="container">
            <p class="title has-text-centered">
                Ajouter un document
            </p>
            <p class="subtitle has-text-centered">
                <%=request.getAttribute("nom")%>
            </p>
        </div>
    </div>
    <form class="form is-8 section is-success is-vcentered" action="${pageContext.request.contextPath}/Ajout"
          method="post">

        <div class="field">
            <label class="label">Type</label>
            <div class="control">
                <input type="radio" name="type" value="DVD" checked>
                <i class="fa-solid fa-film"></i>

                <input type="radio" name="type" value="CD">
                <i class="icon fa-solid fa-compact-disc"></i>

                <input type="radio" name="type" value="Livre">
                <i class="icon fa-solid fa-book"></i>

            </div>
        </div>
        <div class="field">
            <label class="label">Titre</label>
            <div class="control">
                <input class="input" type="text" placeholder="Titre du document" name="titre">
            </div>
        </div>
        <div class="field">
            <label class="label">Auteur</label>
            <div class="control">
                <input class="input" type="text" placeholder="Auteur du document" name="auteur">
            </div>
        </div>
        <div class="buttons are-medium is-grouped-centered">
            <button type="submit" class="button is-danger"
                    formaction="${pageContext.request.contextPath}/Index">
                Revenir à l'index
            </button>
            <button type="submit" class="button is-dark" name="choix" value="ajouter">
                Ajouter le document
            </button>
        </div>
    </form>
</div>
<div class="hero-foot"></div>
<div class="footer content has-text-centered has-text-white" style="background-color: hsl(171, 100%, 41%)">
    <p class="subtitle mb-2 has-text-white">Merci de vous déconnecter avant de quitter le poste :-)</p>
</div>
</body>
<script>
    $(document).ready(function () {

        function displayTime() {
            var currentTime = new Date();

            var hours = currentTime.getHours();
            var meridiem = "AM";
            if (hours > 12) {
                hours = hours - 12;
                meridiem = "PM"
            }
            var minutes = currentTime.getMinutes();
            if (minutes < 10) {
                minutes = "0" + minutes;
            }
            var seconds = currentTime.getSeconds();
            if (seconds < 10) {
                seconds = "0" + seconds;

            }

            var clockDiv = document.getElementById('clock');
            clockDiv.innerText = hours + ":" + minutes + ":" + seconds + " " + meridiem;

        }

        displayTime();
        setInterval(displayTime, 1000);
    });
</script>
</html>
