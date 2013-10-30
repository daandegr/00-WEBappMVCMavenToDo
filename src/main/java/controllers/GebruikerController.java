package controllers;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import models.User;

public class GebruikerController extends HttpServlet {

    /* HTTP GET request */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession sessie = request.getSession();
        List<User> gebruikers;

        // Als gebruikers al in de sessie staan opgeslagen, worden deze opgehaald 
        // en in een LinkedList opgeslagen
        if (sessie.getAttribute("gebruikers") != null) {
            gebruikers = (List) sessie.getAttribute("gebruikers");
        } else {
            // Anders wordt een nieuwe lege LinkedList aangemaakt
            gebruikers = new LinkedList();
        }

        // Zet de lijst met gebruikers en het totaal aantal gebruikers op het request
        request.setAttribute("gebruikers", gebruikers);
       // request.setAttribute("aantalGebruikers", gebruikers.size());

        // Stuur het resultaat van gebruikers.jsp terug naar de client
        String address = "/gebruikers.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
