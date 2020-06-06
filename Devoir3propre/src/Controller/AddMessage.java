package Controller;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Forum;
import Model.Message;
import Model.User;

/**
 * Servlet implementation class AddMessage
 */
@WebServlet("/AddMessage")
public class AddMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		System.out.println(session);
		User u =(User) session.getAttribute("user");
		System.out.println(session.getAttribute("user"));
		int forumId= (int) session.getAttribute("forumId");
		try {
			
			System.out.println(forumId);
			Forum forum= new Forum(forumId);
			System.out.println(forum.getId());
			Message message = new Message();
			message.setEditor(u);
			message.setContent(request.getParameter("Text_message"));
			//request.setAttribute("forums", Forum.addMessage(request.getParameter("Text_message"),u,forum));
			forum.addMessage(message);
			request.setAttribute("id", forumId);
			System.out.println(forum.getId());
			forum.save();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(forumId);
	    RequestDispatcher rd = request.getRequestDispatcher("AfficheMessage?id="+forumId);
	    rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
