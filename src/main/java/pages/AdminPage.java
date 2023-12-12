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
import pojos.Candidate;
import pojos.User;

/**
 * Servlet implementation class AdminPage
 */
@WebServlet(urlPatterns = "/adminpage")
public class AdminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		CandidateDaoImpl cd=(CandidateDaoImpl) session.getAttribute("candidate_dao");
		UserDaoImpl ud=(UserDaoImpl) session.getAttribute("user_dao");
		User u=(User) session.getAttribute("user_info");
		try(PrintWriter pw =response.getWriter()){
		pw.print("<h1>TOP 2 Candidate : </h1>");
		cd.getTopTwo().forEach(v->pw.print("<h3>"+v+"<h3>"));
		pw.print("<h1>ALL PARTY VOTES : </h1>");
		cd.getPartyWise().forEach((k,v)->pw.print("<h3>"+k+" "+v+"<h3>"));
		session.invalidate();
		pw.print("<a href='login.html'>log out </a>");
		}
		 catch (Exception e) {
			throw new ServletException("Error in toptwo",e);
		}
		
	}

}
