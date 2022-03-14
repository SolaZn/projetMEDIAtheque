package fr.uparis.services;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "Ajout", value = "/Ajout")
public class Ajout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // toujours vérifier si l'utilisateur est loggé (si ce n'est pas le cas, retour immédiat à /Connexion)
        isUserLogged(request, response);

        //vérifier le statut de l'utilisateur
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");


        if(!utilisateur.isBibliothecaire()){
            request.setAttribute("notAllowed", "true");

            RequestDispatcher rd = request.getRequestDispatcher("/Index");
            rd.forward(request, response);
        }
        //mise en place des attributs
        request.setAttribute("nom", utilisateur.name());

        //envoi au JSP pour traitement et affichage
        RequestDispatcher rd = request.getRequestDispatcher("view/ajout.jsp");
        rd.forward(request,response);
    }

    private void isUserLogged(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("utilisateur") == null){
            request.setAttribute("loginfailed", "failed");
            request.setAttribute("notConnected", "true");

            RequestDispatcher rd = request.getRequestDispatcher("/Connexion");
            rd.forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // vérification de connexion
        isUserLogged(request, response);

        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");

        // vérification de statut
        if(!utilisateur.isBibliothecaire()){
            request.setAttribute("notAllowed", "true");

            RequestDispatcher rd = request.getRequestDispatcher("/Index");
            rd.forward(request, response);
        }

        // récupération des paramètres retournés par le formulaire
        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        String type = request.getParameter("type");
        Object[] o = {titre, auteur};

        try {
            Mediatheque.getInstance().ajoutDocument(getTypeNumber(type), o);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/Index");
    }

    private int getTypeNumber(String type) throws Exception {
        switch (type.toUpperCase(Locale.ROOT)){
            case "Livre":{
                return 1;
            }
            case "DVD":{
                return 2;
            }
            case "CD":{
                return 3;
            }
            default:{
                throw new Exception();
            }
        }
    }
}
