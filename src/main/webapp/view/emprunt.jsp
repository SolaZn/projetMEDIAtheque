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
    <title>Emprunt - Médiathèques de la vallée de San Diego</title>
    <link rel="icon" href="${pageContext.request.contextPath}/view/content/logo.png">
</head>
<body style="background-color: hsl(171, 100%, 41%)">
<div class="hero is-info is-large is-fullheight-with-navbar">
    <div class="hero-head" style="background-color: #E8E1E1;">
        <nav class="navbar is-spaced">
            <div class="container">
                <div class="navbar-brand">
                    <img src="${pageContext.request.contextPath}/view/content/logo_small.png" alt="Logo">
                </div>
            </div>
        </nav>
    </div>

    <div class="hero-body">
        <div class="container has-text-centered">
            <p class="title">
                Bonjour <%String[] user = (String[]) session.getAttribute("utilisateur");%> <%=user[0]%>
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
</html>
