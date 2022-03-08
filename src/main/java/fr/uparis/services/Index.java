package fr.uparis.services;

import mediatek2022.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Index", value = "/Index")
public class Index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // toujours v�rifier si l'utilisateur est logg� (si ce n'est pas le cas, retour imm�diat � /Connexion)
        isUserLogged(request, response);

        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");

        //mise en place des attributs
        request.setAttribute("nom", utilisateur.name());

        if(utilisateur.isBibliothecaire()){
            request.setAttribute("bibliothecaire", "true");
        }else{
            request.setAttribute("bibliothecaire", "false");
        }

        RequestDispatcher rd = request.getRequestDispatcher("view/index.jsp");
        rd.forward(request, response);
    }

    private void isUserLogged(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getSession().getAttribute("utilisateur") == null){
            request.setAttribute("loginfailed", "failed");
            request.setAttribute("notConnected", "true");

            RequestDispatcher rd = request.getRequestDispatcher("/Connexion");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // toujours v�rifier si l'utilisateur est logg� (si ce n'est pas le cas, retour imm�diat � /Connexion)
        isUserLogged(request, response);

        String choix = request.getParameter("choix");
        if(choix != null){
            switch(choix){
                case "rendre":
                    response.sendRedirect(request.getContextPath() + "/Rendu");
                    break;
                case "emprunter":
                    response.sendRedirect(request.getContextPath() + "/Emprunt");
                    break;
                case "deconnexion":
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/Connexion");
                    break;
                case "ajouter":
                    response.sendRedirect(request.getContextPath() + "/Ajout");
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/Index");
                    break;
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/Index");
        }
    }
}
