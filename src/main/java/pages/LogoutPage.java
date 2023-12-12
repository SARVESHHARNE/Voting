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
 * Servlet implementation class LogoutPage
 */
@WebServlet("/logoutpage")
public class LogoutPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		User u=(User) session.getAttribute("user_info");
		try(PrintWriter pw=response.getWriter()){
			pw.print("<h1>Thank you "+u.getFname()+"</h1>");
			pw.print("<h3>you already voted!!</h3>");
			System.out.println(session.getId());
			session.invalidate();
			System.out.println(session.getId());
			pw.print("<a href='login.html'>logout</a>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u=(User) session.getAttribute("user_info");
		UserDaoImpl ud=(UserDaoImpl) session.getAttribute("user_dao");
		CandidateDaoImpl cd=(CandidateDaoImpl) session.getAttribute("candidate_dao");
		try(PrintWriter pw=response.getWriter()){
			ud.updateVoterStatus(u.getId());
			cd.updateVotes(Integer.parseInt(request.getParameter("cd")));
			pw.print("<h1>Thank you "+u.getFname()+"</h1>");
			pw.print("<h3>you vote is registerd </h3>");
			session.invalidate();
			pw.print("<a href='login.html'>logout</a>");
		} catch (SQLException e) {
			throw new ServletException("error in logout", e);
		}
	}

}
