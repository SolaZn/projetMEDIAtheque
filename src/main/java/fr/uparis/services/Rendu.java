package fr.uparis.services;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Rendu", value = "/Rendu")
public class Rendu extends HttpServlet {
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
        RequestDispatcher rd = request.getRequestDispatcher("view/rendu.jsp");
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

        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");


        String choix = request.getParameter("choix");
        RequestDispatcher rd = request.getRequestDispatcher("/Index");

        if(choix != null){
            if(choix.equals("retour")){
                String numero = request.getParameter("numero");
                // synchronized ?
                List<Document> listeDocumentsDisp = Mediatheque.getInstance().tousLesDocumentsDisponibles();
                try {
                    Mediatheque.getInstance().retour(listeDocumentsDisp.get(Integer.parseInt(numero) - 1), utilisateur);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            request.setAttribute("done", "false");
        }
        request.setAttribute("done", "true");
        rd.forward(request,response);
    }
}
