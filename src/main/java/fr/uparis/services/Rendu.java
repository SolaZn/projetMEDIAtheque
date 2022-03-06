package fr.uparis.services;

import mediatek2022.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Rendu", value = "/Rendu")
public class Rendu extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // toujours v�rifier si l'utilisateur est logg� (si ce n'est pas le cas, retour imm�diat � /Connexion)
        isUserLogged(request, response);

        //v�rifier le statut de l'utilisateur
        SerializableData user = (SerializableData) request.getSession().getAttribute("utilisateur");
        Utilisateur utilisateur = (Utilisateur) user.getObjet();

        if(utilisateur.isBibliothecaire()){
            request.setAttribute("notAllowed", "true");

            RequestDispatcher rd = request.getRequestDispatcher("/Index");
            rd.forward(request, response);
        }

        

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

    }
}
