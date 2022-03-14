package fr.uparis.services;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Connexion", value = "/Connexion")
public class Connexion extends HttpServlet {
    private boolean mediathequeInitialized = false;
    private static void initialize(){
        try {
            Class.forName(DataConfiguration.getConfigPackage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // si la médiathèque n'a pas déjà été chargée
        if(!mediathequeInitialized){
            initialize();
            mediathequeInitialized = true;
        }
        RequestDispatcher rd = request.getRequestDispatcher("view/connexion.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;

        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // récupération de l'utilisateur dans la Médiathèque
        Utilisateur utilisateur = Mediatheque.getInstance().getUser(login, password);

        if(utilisateur != null) {
            // mise en place de la variable utilisateur
            session.setAttribute("utilisateur", utilisateur);
            response.sendRedirect(request.getContextPath() + "/Index");
        }else{
            request.setAttribute("loginfailed", "failed");
            rd = request.getRequestDispatcher("view/connexion.jsp");
            rd.forward(request,response);
        }
     }
}
