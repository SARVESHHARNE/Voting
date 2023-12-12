package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.CandidateDaoImpl;
import Dao.UserDaoImpl;
import pojos.User;

/**
 * Servlet implementation class CandidateList
 */
@WebServlet("/candidatelist")
public class CandidateList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		User u=(User) session.getAttribute("user_info");
		UserDaoImpl ud =(UserDaoImpl) session.getAttribute("user_dao");
		CandidateDaoImpl cd=(CandidateDaoImpl) session.getAttribute("candidate_dao");
		
		try(PrintWriter pw=response.getWriter()){
			pw.print("<h1>Hello "+u.getFname()+"</h1>");
			pw.print("<form method='post' action='logoutpage' >");
			cd.getAll().forEach(c->pw.print("<input type='radio' name='cd' value='"+c.getId()+"'>"+c.getName()+"</input></br>"));
			pw.print("<input type='submit' value='vote'>");
			pw.print("</form>");
		} catch (Exception e) {
			throw new ServletException("erro in voting ",e);
		}
	}

}
