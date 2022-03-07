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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/connexion.css">
    <title>Rendre un document - Médiathèques de la vallée de San Diego</title>
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
                Emprunter un document
            </p>
            <p class="subtitle has-text-centered">
                <%=request.getAttribute("nom")%>
            </p>
        </div>
    </div>
    <form class="section is-success is-vcentered" action="${pageContext.request.contextPath}/Emprunt"
          method="post">
        <p class="subtitle is-6">Liste des documents disponibles</p>
        <table class="table">
            <thead>
            <tr>
                <th><abbr title="Numéro du document">ID</abbr></th>
                <th>Type</th>
                <th><abbr title="État d'emprunt du document">État</abbr></th>
                <th>Titre</th>
                <th>Auteur</th>
                <th>Options</th>
                <th>Sélectionner</th>
            </tr>
            </thead>
            <tbody>

                <%
                    String noDocument = (String) request.getAttribute("noDocument");
                    if (noDocument != null) {
                        if (noDocument.equals("false")) {
                            List<Document> documentList = (List<Document>) request.getAttribute("documents");
                            if(documentList != null){
                                int count = 0;
                                for(Document doc : documentList){
                %>
            <tr>
                <th><%=doc.toString()%>
                </th>
                <td><%=doc.toString()%>
                </td>
                <td><%=doc.toString()%>
                </td>
                <td><%=doc.toString()%>
                </td>
                <td><%=doc.toString()%>
                </td>
                <td><%=doc.toString()%>
                </td>
                <%
                    if (!doc.disponible()) {
                %>
                <td><input type="radio" name="numero" value=<%=count%>></td>
                <%} else {%>
                <td><input type="radio" name="numero" disabled></td>
                <%}
                count++;%>
            </tr>
                <%
                                }
                                %>
            <tfoot>
            <tr>
                <th><abbr title="Numéro du document">ID</abbr></th>
                <th>Type</th>
                <th><abbr title="État d'emprunt du document">État</abbr></th>
                <th>Titre</th>
                <th>Auteur</th>
                <th>Options</th>
                <th>Sélectionner</th>
            </tr>
            </tfoot>
            <%
                    }
                }
            } else {
            %>
            <p class="content">Aucun document</p>
            <% }
            %>
        </table>
        <div class="buttons are-medium is-grouped-centered">
            <button type="submit" class="button is-danger"
                    formaction="${pageContext.request.contextPath}/Index">
                Revenir à l'index
            </button>
            <button type="submit" class="button is-dark" name="choix" value="emprunter">
                Emprunter le document
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
</html>
