import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserManager
 */
@WebServlet("/UserManager")
public class UserManager extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private static Hashtable<Integer,User> usersTable = new Hashtable<Integer,User>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManager() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setContentType("text/html");// setting the content type
		PrintWriter pw = res.getWriter();//get the stream to write the data
				
		System.out.println("blzekrfjzlek");
		
		//writing html in the stream
		pw.println("<html><body>");
		pw.println("Welcome to servlet");
		
		for(int i = 0; i< usersTable.size(); i++) {
				pw.println(usersTable.get(i));
				pw.println("blabla");
		
		}

		pw.println("</body></html>");
		pw.close();//closing the stream

		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		
		String firstname = request.getParameter("User first name");
		String lastname = request.getParameter("User family name");
		String email = request.getParameter("User email");
		String password = request.getParameter("User password");
		String male = request.getParameter("gender");
		String female = request.getParameter("gender");
				
		User newUser = new User(firstname, lastname, email, password, male, female);
		
		for(int i = 0; i< usersTable.size(); i++) {
			if(!usersTable.get(i).equals(newUser)) 	{
				usersTable.put(usersTable.size(), newUser);
				System.out.println(newUser);
			}else System.out.println("Données erronnées");

		}
		
		PrintWriter pw = response.getWriter();//get the stream to write the data
		pw.println("sdfgsd");
		
	}

}
