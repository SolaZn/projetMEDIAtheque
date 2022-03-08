<%--
  Created by IntelliJ IDEA.
  User: Anthony
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/connexion.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <title>Index - Médiathèques de la vallée de San Diego</title>
    <link rel="icon" href="${pageContext.request.contextPath}/view/content/logo_favi.png">
</head>
<body style="background-color: hsl(204, 86%, 53%);">
<div class="hero is-info is-large is-fullheight-with-navbar">
    <div class="hero-head" style="background-color: #E8E1E1;">
        <nav class="navbar is-spaced">
            <div class="container">
                <div class="navbar-brand">
                    <img src="${pageContext.request.contextPath}/view/content/logo_small.png" alt="Logo">
                </div>
                <div class="has-text-grey-dark navbar-item" id="clock">
                </div>
            </div>
        </nav>
    </div>

    <div class="hero-body">
        <div class="container has-text-centered">
            <p class="title">
                Bonjour <%=request.getAttribute("nom")%>
            </p>
            <p class="subtitle">
                Bienvenue à la médiathèque de South Antonio St.
            </p>

            <form class="buttons are-large is-grouped is-centered" action="${pageContext.request.contextPath}/Index"
                  method="post">
                <%
                    String statut = (String) request.getAttribute("bibliothecaire");
                    if (statut != null) {
                        if (statut.equals("false")) {
                %>
                <p class="control">
                    <button type="submit" class="button is-link" name="choix" value="rendre">
                        Rendre un document
                    </button>
                </p>
                <p class="control">
                    <button type="submit" class="button" name="choix" value="emprunter">
                        Emprunter un document
                    </button>
                </p>
                <p class="control">
                    <button type="submit" class="button is-danger" name="choix" value="deconnexion">
                        Se déconnecter
                    </button>
                </p>
                <%
                } else if (statut.equals("true")) {
                %>
                <p class="control">
                    <button type="submit" class="button is-link" name="choix" value="ajouter">
                        Ajouter un document
                    </button>
                </p>
                <p class="control">
                    <button type="submit" class="button is-danger" name="choix" value="deconnexion">
                        Se déconnecter
                    </button>
                </p>
                <%
                        }
                    }
                %>
            </form>
        </div>
    </div>

    <div class="hero-foot"></div>
    <div class="footer content has-text-centered has-text-white is-info" style="background-color: #3e8ed0;">
        <p class="subtitle mr-2">Merci de vous déconnecter avant de quitter le poste :-)</p>
    </div>
</div>
</body>
<script>
    $(document).ready(function() {

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
<!-- TODO: ajouter les modals JS pour les erreurs -->
</html>
