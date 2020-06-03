/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.exit;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lounis
 */
@WebServlet(name = "UserManager", urlPatterns = {"/UserManager"})
public class UserManager extends HttpServlet {

    private static Hashtable<Integer, User> usersTable = new Hashtable<Integer, User>();

    public static Hashtable<Integer, User> getUsersTable() {
        return usersTable;
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Liste des utilisateurs </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Liste des utilisateurs : </h1>");
            out.println("<ol>");

            Set<Integer> keys = usersTable.keySet();

            //Obtaining iterator over set entries
            Iterator<Integer> itr = keys.iterator();
            while (itr.hasNext()) {
                out.println("<li>");
                out.println(usersTable.get(itr.next()).toString());
                out.println("</li>");
            }
            out.println("</ol>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String firstName = request.getParameter("User first name");
        String lastName = request.getParameter("User familly name");
        String mail = request.getParameter("User email");
        String gender = request.getParameter("gender");
        String password = request.getParameter("User password");

        boolean valid = true;

        if (firstName == null || lastName == null || mail == null || password == null) {
            System.out.println("Champs non renseignés");
            RequestDispatcher rd = request.getRequestDispatcher("NouveauUtilisateur.html");
            rd.forward(request, response);
            valid = false;

        } else if ("".equals(firstName) || "".equals(lastName) || "".equals(mail) || "".equals(password)) {
            System.out.println("Champs vides");
            RequestDispatcher rd = request.getRequestDispatcher("NouveauUtilisateur.html");
            rd.forward(request, response);
            valid = false;

        }
        if (request.getParameter("validator") != null) {

            if ("oui".equals(request.getParameter("valider"))) {// on insère les doublons
                valid = true;
            } else {
                valid = false;
                RequestDispatcher rd = request.getRequestDispatcher("NouveauUtilisateur.html");//abandonner l'insertion
                rd.forward(request, response);
            }

        } else if (UserManager.getUsersTable().containsValue(new User(lastName, firstName))) {
            valid = false;
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Confirmation</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Un utilisateur avec les mêmes nom et prénom existe déjà. Voulez-vous l'enregistrer ?  </h1>");
                out.println("<form method='post' action='UserManager'>");
                out.println("Oui <input type='radio' name='valider' value='oui' /> ");
                out.println("Nom <input type='radio' name='valider' value='nom' />");
                out.println("<input type='hidden' name='User first name' value='" + firstName + "'/>");
                out.println("<input type='hidden' name='User familly name' value='" + lastName + "'/>");
                out.println("<input type='hidden' name='User email' value='" + mail + "'/>");
                out.println("<input type='hidden' name='gender' value='" + gender + "'/>");
                out.println("<input type='hidden' name='User password' value='" + password + "' />");
                out.println("<br>");
                out.println("<input type ='submit' value='Envoyer' name='validator' />");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
            }

        }
        if (valid) {
            usersTable.put(usersTable.size(), new User(lastName, firstName, mail, usersTable.size(), gender, password));
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Un nouveau utilisateur </title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> Un nouveau utilisateur est ajouté : </h1>");
                out.println(usersTable.get(usersTable.size() - 1).toString());
                out.println("</body>");
                out.println("</html>");

            }

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
