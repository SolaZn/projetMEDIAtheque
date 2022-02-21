package fr.uparis.services;

import mediatek2022.Mediatheque;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Connexion", value = "/Connexion")
public class Connexion extends HttpServlet {
    Mediatheque m;

    Connexion(){
        /*try{
            Class.forName(persistance.MediathequeData.class.getName());
        }catch(Exception e){

        }*/
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("view/connexion.jsp");
        response.sendRedirect("view/index.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
