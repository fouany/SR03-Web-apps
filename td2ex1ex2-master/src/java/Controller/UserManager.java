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

@WebServlet(name = "UserManager", urlPatterns = {"/UserManager"})
public class UserManager extends HttpServlet {
    private static  Hashtable<Integer, User> usersTable= new Hashtable<Integer, User>();

    public static Hashtable<Integer, User> getUsersTable() {
        return usersTable;
    }
    /**
     * Processes requests for both HTTP  <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String firstName=request.getParameter("User first name");
        String lastName=request.getParameter("User familly name");
        String mail=request.getParameter("User email");
        String gender=request.getParameter("gender");
        String password=request.getParameter("User password");  
        String role = request.getParameter("admin");
        
        usersTable.put(usersTable.size(), new User(lastName,firstName,mail,usersTable.size(),gender,password ));
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Un nouveau utilisateur </title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Un nouveau utilisateur est ajout� : </h1>");
            out.println(usersTable.get(usersTable.size()-1).toString());
            out.println("</body>");
            out.println("</html>");
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            out.println("<title>Un nouveau utilisateur </title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Liste des utilisateurs : </h1>");
            out.println("<ol>");        
        
            Set<Integer> keys = usersTable.keySet();
 
            //Obtaining iterator over set entries
            Iterator<Integer> itr = keys.iterator();
            while(itr.hasNext()){
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
        processRequest(request, response);
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
