package fr.uparis.services;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Emprunt", value = "/Emprunt")
public class Emprunt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // toujours vérifier si l'utilisateur est loggé (si ce n'est pas le cas, retour immédiat à /Connexion)
        isUserLogged(request, response);

        //vérifier le statut de l'utilisateur
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");

        if(utilisateur.isBibliothecaire()){
            request.setAttribute("notAllowed", "true");

            RequestDispatcher rd = request.getRequestDispatcher("/Index");
            rd.forward(request, response);
        }

        //mise en place des attributs
        request.setAttribute("nom", utilisateur.name());

        //affichage de la liste des documents disponibles
        List<Document> listeDocumentsDisp = Mediatheque.getInstance().tousLesDocumentsDisponibles();
        if(listeDocumentsDisp.isEmpty()){
            request.setAttribute("noDocument","true");
        }else{
            request.setAttribute("noDocument","false");
            request.setAttribute("documents", listeDocumentsDisp);
        }

        //envoi au JSP pour traitement et affichage
        RequestDispatcher rd = request.getRequestDispatcher("view/emprunt.jsp");
        rd.forward(request,response);
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

        //vérifier le statut de l'utilisateur
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");

        if(utilisateur.isBibliothecaire()){
            request.setAttribute("notAllowed", "true");

            RequestDispatcher rd = request.getRequestDispatcher("/Index");
            rd.forward(request, response);
        }

        String choix = request.getParameter("choix");
        if(choix != null){
            if(choix.equals("emprunter")){
                String numero = request.getParameter("numero");
                List<Document> listeDocumentsDisp = Mediatheque.getInstance().tousLesDocumentsDisponibles();
                try {
                    Mediatheque.getInstance().emprunt(listeDocumentsDisp.get(Integer.parseInt(numero) - 1), utilisateur);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/Index");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/Index");
        request.setAttribute("done", "true");
        rd.forward(request,response);
    }
}
