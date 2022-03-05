<%--
  Created by IntelliJ IDEA.
  User: pierr
  Date: 21/02/2022
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Emprunt</title>
</head>
<body>
  <section class="section login">
    <!-- TITLE -->
    <h2 class="section__title">Emprunt</h2>
    <div class="login__container container">
        <div class="login__content">
            <div class="login__img">
                <img src="./vue/img/login-img.svg" alt="login">
            </div>
            <!-- FORM -->
            <div class="login__form">
                <form action="./index.jsp?controle=user&action=ident" method="post" class="login__register" id="login-in">
                    <h3 class="login__title">Emprunt</h3>
                    <% for (Document d : documents) { %>
                      <a href="" onClick="emprunter(d)"><%=d.toString() %></a><br>
                    <%}%>            
          
                <!--</form>-->
            </div>
        </div>
    </div>
</section>
</body>
</html>
