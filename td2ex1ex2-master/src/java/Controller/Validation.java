package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.User;

/**
 * Servlet implementation class Validation
 */
@WebServlet(name = "Validation", urlPatterns = {"/Validation"})
public class Validation extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
    private static Hashtable<Integer, User> usersTable = new Hashtable<Integer, User>();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//RequestDispatcher rd = request.getRequestDispatcher("NouveauUtilisateur.html");
        //rd.forward(request, response);
		
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

	        	try (PrintWriter out = response.getWriter()) {
	                /* TODO output your page here. You may use following sample code. */
	                out.println("<!DOCTYPE html>");
	                out.println("<html>");
	                out.println("<head>");
	                out.println("<title> Un nouveau utilisateur </title>");
	                out.println("</head>");
	                out.println("<body>");
	                out.println("<h1> Un nouveau utilisateur est ajouté : </h1>");
	                out.println(usersTable.get(usersTable.size() - 1).toString());
	                out.println("</body>");
	                out.println("</html>"); 
	            }

	        }
	     
		
	}
		
}
