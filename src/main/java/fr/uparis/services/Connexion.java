package fr.uparis.services;

import fr.uparis.persistance.ConnexionData;
import mediatek2022.Mediatheque;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Connexion", value = "/Connexion")
public class Connexion extends HttpServlet {
    Mediatheque m;
    ConnexionData c;

    public Connexion(){
        try {
            Class.forName(fr.uparis.persistance.MediathequeData.class.getName());
            ConnexionData.initialize();
            c = new ConnexionData();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("view/connexion.jsp");
        try {
            List<String> utilisateurs = c.getUtilisateur();
            if(request.getAttribute("users") == null)
                request.setAttribute(   "users", utilisateurs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
