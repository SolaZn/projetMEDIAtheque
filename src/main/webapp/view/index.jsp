<%@ page import="fr.uparis.persistance.Utilisateur" %><%--
  Created by IntelliJ IDEA.
  User: pierr
  Date: 21/02/2022
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<body>
<%
    String[] utilisateur = (String[]) session.getAttribute("utilisateur");
%>
<%=utilisateur[2]%>
</body>
</html>
