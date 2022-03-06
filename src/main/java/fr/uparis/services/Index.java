package fr.uparis.services;

import fr.uparis.persistance.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Index", value = "/Index")
public class Index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // toujours vérifier si l'utilisateur est loggé (si ce n'est pas le cas, retour immédiat à /Connexion)
        isUserLogged(request, response);

        // afficher la liste des actions selon le profil
        String[] donneesUtilisateur = (String[]) request.getSession().getAttribute("utilisateur");
        Object[] array = {donneesUtilisateur[2]};
        Utilisateur utilisateur = new Utilisateur(donneesUtilisateur[0], Boolean.parseBoolean(donneesUtilisateur[1]), array);

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
        // toujours vérifier si l'utilisateur est loggé (si ce n'est pas le cas, retour immédiat à /Connexion)
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
