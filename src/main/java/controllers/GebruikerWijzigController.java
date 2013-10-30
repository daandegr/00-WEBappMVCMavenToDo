package controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import models.Rol;
import models.User;
import models.UserForm;
import validators.UserValidator;

public class GebruikerWijzigController extends HttpServlet {

    private static String titelNieuw = "Nieuwe gebruiker"; //Titel voor de Nieuwe gebruiker pagina
    private static String titelWijzig = "Wijzigen gebruiker"; //Titel voor de Wijzig gebruiker pagina

    /* HTTP GET request */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (request.getParameter("id") != null) {
            //Als er een id is meegegeven, worden de gegevens van de gebruiker opgehaald.
            long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("id", id); // TODO: why?

            // Haal een sessie object op uit het request
            HttpSession sessie = request.getSession();
            LinkedList gebruikers = (LinkedList) sessie.getAttribute("gebruikers"); //Haalt de lijst met gebruikers op en slaat deze op in een LinkedList

            for (int i = 0; i < gebruikers.size(); i++) {
                User tempGebruiker = (User) gebruikers.get(i);

                //Als de gebruiker overeenkomt met het gegeven id, worden de gegevens ingevuld in het formulier.
                if (tempGebruiker.getCustomerNumber() == id) {
                    request.setAttribute("naam", tempGebruiker.getName());
                    request.setAttribute("adres", tempGebruiker.getStreetAddress());
                    request.setAttribute("plaats", tempGebruiker.getCity());
                    request.setAttribute("huisNummer", tempGebruiker.getHuisNummer());
                }
            }
            doorsturen(request, response, titelWijzig); //Stuurt door naar de Wijzig gebruiker pagina.
        } else {
            doorsturen(request, response, titelNieuw); //Stuurt door naar de Nieuwe gebruiker pagina.
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String dispatchUrl = null;

        long id = 0;
        long rolId = Long.parseLong(request.getParameter("rol"));

        List<String> errors = new ArrayList<String>();
        UserForm userform = new UserForm();

        userform.setName(request.getParameter("naam"));
        userform.setStreetAddress(request.getParameter("straatnaam"));
        userform.setHuisNummer(request.getParameter("huisNummer"));
        userform.setCity(request.getParameter("plaats"));
        userform.setRol(getRol(rolId, request));


        if (request.getParameter("id") != null) {
            id = Long.parseLong(request.getParameter("id"));
        }

        if (request.getParameter("role") != null) {
            rolId = Long.parseLong(request.getParameter("role"));
        }
        // validate userForm
        UserValidator userValidator = new UserValidator();
        errors = userValidator.validate(userform);


        if (!errors.isEmpty()) {  // zolang er errors zijn , keer terug naar het invulformulier
            request.setAttribute("paginaTitel", "Error:");
            request.setAttribute("foutMelding", errors);
            request.setAttribute("form", userform);
            request.setAttribute("naam", userform.getName());
            request.setAttribute("adres", userform.getStreetAddress());
            request.setAttribute("plaats", userform.getCity());
            request.setAttribute("huisNummer", userform.getHuisNummer());

            dispatchUrl = "/gebruiker_wijzigen.jsp";

        } else {
            HttpSession sessie = request.getSession(); //Haalt de gegevens uit de sessie op.
            LinkedList<User> gebruikers = new LinkedList(); //Maakt nieuwe LinkedList aan voor het opslaan van de gebruikers.
            if (sessie.getAttribute("gebruikers") != null) {
                gebruikers = (LinkedList) sessie.getAttribute("gebruikers"); //Haalt de huidige lijst met gebruikers op uit de sessie.

            }

            String naam, straatnaam, plaats;
            int huisnummer;

            naam = userform.getName();
            straatnaam = userform.getStreetAddress();
            huisnummer = Integer.parseInt(userform.getHuisNummer());
            plaats = userform.getCity();
            
            Rol rol = userform.getRol();

            //Als er een id is meegestuurd, wordt de gebruiker geupdate.
            if (id != 0) {
                LinkedList<User> tempGebruikers = new LinkedList();

                for (int i = 0; i < gebruikers.size(); i++) {
                    User tempGebruiker = (User) gebruikers.get(i);

                    //Als de gebruiker overeenkomt met het gegeven id, wordt de gebruiker geupdate.
                    if (tempGebruiker.getCustomerNumber() == id) {
                        tempGebruiker.setName(naam);
                        tempGebruiker.setStreetAddress(straatnaam);
                        tempGebruiker.setHuisNummer(huisnummer);
                        tempGebruiker.setCity(plaats);
                        tempGebruiker.setRol(rol);
                    }
                    tempGebruikers.add(tempGebruiker);
                }
                gebruikers = tempGebruikers;
            } else {//Anders wordt de nieuwe gebruiker aangemaakt.
                long uniekId = System.nanoTime();
                User gebruiker = new User(uniekId, naam, straatnaam, plaats, huisnummer, rol); //huisnummer, rolenummer);
                gebruikers.add(gebruiker);
            }

            sessie.setAttribute("gebruikers", gebruikers);

            response.sendRedirect("../gebruikers");

        }
        
        if (dispatchUrl != null) {
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
//        // Haal een sessie object op uit het request
//        HttpSession sessie = request.getSession();
//        
//        // Als the parameter 'id' niet null is, dan hebben we te maken met
//        // een user die wordt geupdate
//        boolean isUserUpdate = request.getParameter("id") != null;
//
//        // Haal de lijst met gebruikers op uit de sessie
//        List<User> gebruikers = (List) sessie.getAttribute("gebruikers");
//        
//        // Controleer of de lijst met gebruikers niet null is, zo ja, 
//        // creër een lege lijst en zet deze op de sessie
//        if (gebruikers == null) {
//            gebruikers = new LinkedList<User>();
//        }
//        
//        // Zet de form parameters om in een User object
//        User formUser = getUserFromRequest(request);
//        
//        // Indien we hier met een gebruikers update te maken hebben, dan halen
//        // we deze gebruiker op uit de lijst met gebruikers en wijzigen we zijn
//        // gegevens
//        if (isUserUpdate) {
//            for (int i = 0; i < gebruikers.size(); i++) {
//                User tempGebruiker = (User) gebruikers.get(i);
//                
//                // Als het CustomerNumber van de tempGebruiker overeenkomt met 
//                // het CutomerNumber van de formUser, dan wordt de gebruiker geupdate.
//                if (tempGebruiker.getCustomerNumber() == formUser.getCustomerNumber()) {
//                    tempGebruiker.setName(formUser.getName());
//                    tempGebruiker.setStreetAddress(formUser.getStreetAddress());
//                    tempGebruiker.setCity(formUser.getCity());
//                }
//            }
//        } else {
//            // Anders zetten we een uniek id op het User object en voegen we 
//            // deze als nieuwe gebruiker toe aan de lijst met gebruikers
//            long uniekId = System.nanoTime();
//            formUser.setCustomerNumber(uniekId);
//            gebruikers.add(formUser);
//        }
//
//        sessie.setAttribute("gebruikers", gebruikers);
//
//        sessie.setAttribute("aantalGebruikers", gebruikers.size()); 
//        response.sendRedirect("../gebruikers");
    }

    private void doorsturen(HttpServletRequest request, HttpServletResponse response, String titel)
            throws ServletException, IOException {       
        HttpSession sessie = request.getSession();
        List<Rol> rollen;
        
        if (sessie.getAttribute("rollen") != null) {
            rollen = (List) sessie.getAttribute("rollen");
        } else {
            rollen = new LinkedList();
        }

        request.setAttribute("paginaTitel", titel);
        request.setAttribute("rollen", rollen);
        
        // Stuur het resultaat van gebruiker_wijzigen.jsp terug naar de client
        String address = "/gebruiker_wijzigen.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    /**
     * Maakt een User object aan de hand van de parameters uit het http request.
     */
    private User getUserFromRequest(HttpServletRequest request) {
        User u = new User();

        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
            u.setCustomerNumber(Long.parseLong(request.getParameter("id")));
        }
        if (request.getParameter("naam") != null) {
            u.setName(request.getParameter("naam"));
        }
        if (request.getParameter("straatnaam") != null) {
            u.setStreetAddress(request.getParameter("straatnaam"));
        }
        if (request.getParameter("plaats") != null) {
            u.setCity(request.getParameter("plaats"));
        }

        return u;
    }
    
    public Rol getRol(long id, HttpServletRequest request){
        HttpSession sessie = request.getSession();
        List<Rol> rollen;
        
        if (sessie.getAttribute("rollen") != null) {
            rollen = (List) sessie.getAttribute("rollen");
        } else {
            rollen = new LinkedList();
        }
        for (int i = 0; i < rollen.size(); i++) {
                Rol tempRol = (Rol) rollen.get(i);

                if (tempRol.getRolNumber() == id) {
                    return tempRol;
                }
            }
        
        return rollen.get(0);
    }
}
