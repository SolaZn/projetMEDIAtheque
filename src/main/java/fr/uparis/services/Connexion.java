package fr.uparis.services;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "Connexion", value = "/Connexion")
public class Connexion extends HttpServlet {
    public Connexion(){
        try {
            Class.forName(fr.uparis.persistance.MediathequeData.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("view/connexion.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;

        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Utilisateur utilisateur = Mediatheque.getInstance().getUser(login, password);

        if(utilisateur != null) {
            String[] utilisateurData = {utilisateur.name(), String.valueOf(utilisateur.isBibliothecaire()), Arrays.toString(utilisateur.data())};
            session.setAttribute("utilisateur", utilisateurData);
            response.sendRedirect(request.getContextPath() + "/Index");
        }else{
            request.setAttribute("loginfailed", "failed");
            rd = request.getRequestDispatcher("view/connexion.jsp");
            rd.forward(request,response);
        }
     }
}
